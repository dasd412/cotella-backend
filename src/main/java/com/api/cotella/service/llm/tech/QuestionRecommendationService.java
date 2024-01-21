package com.api.cotella.service.llm.tech;

import com.api.cotella.common.util.PromptUtils;
import com.api.cotella.repository.llm.tech.RelatedQuestionLLMRepository;
import com.api.cotella.service.llm.LLMCallService;
import com.api.cotella.service.llm.tech.dto.RecommendedQuestionPairDTO;
import com.api.cotella.service.llm.tech.dto.TechQuestionAnswerPairDTO;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Profile({"local", "dev", "prod"})
@Service
public class QuestionRecommendationService {

  private final RelatedQuestionLLMRepository relatedQuestionLLMRepository;
  private final LLMCallService llmCallService;

  public QuestionRecommendationService(RelatedQuestionLLMRepository relatedQuestionLLMRepository,
      LLMCallService llmCallService) {
    this.relatedQuestionLLMRepository = relatedQuestionLLMRepository;
    this.llmCallService = llmCallService;
  }

  @Transactional
  public List<RecommendedQuestionPairDTO> recommendRelatedTechQuestions(
      List<TechQuestionAnswerPairDTO> techQuestionAnswerPairDTOList) {

    List<RecommendedQuestionPairDTO> recommendedQuestionPairDTOList = new ArrayList<>();

    for (TechQuestionAnswerPairDTO dto : techQuestionAnswerPairDTOList) {

      List<ChatMessage> chatMessages = makeSystemMessageAndUserMessage(dto);

      ChatCompletionResult chatCompletionResult = llmCallService.generate(chatMessages);

      recommendedQuestionPairDTOList.add(makeRecommendedQuestionPairDTO(dto, chatCompletionResult));
    }

    //todo 생성된 연관질문들을 relatedQuestionLLMRepository을 이용해 저장해야 함.

    return recommendedQuestionPairDTOList;
  }

  private List<ChatMessage> makeSystemMessageAndUserMessage(TechQuestionAnswerPairDTO dto) {
    ChatMessage systemMessage = new ChatMessage(ChatMessageRole.SYSTEM.value(),
        PromptUtils.loadPromptForQuestionRecommendation());

    ChatMessage userMessage = new ChatMessage(ChatMessageRole.USER.value(),
        PromptUtils.makeInputPromptForQuestionRecommendation(dto));

    return List.of(systemMessage, userMessage);
  }

  private RecommendedQuestionPairDTO makeRecommendedQuestionPairDTO(TechQuestionAnswerPairDTO dto,
      ChatCompletionResult chatCompletionResult) {
    Integer interviewQuestionId = dto.getInterviewQuestionId();

    String relatedQuestionByLLMRecommendation = chatCompletionResult.getChoices().get(0)
        .getMessage().getContent();

    return new RecommendedQuestionPairDTO(interviewQuestionId, relatedQuestionByLLMRecommendation);
  }
}
