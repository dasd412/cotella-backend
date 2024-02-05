package com.api.cotella.controller.completion;

import com.api.cotella.controller.completion.response.TechQuestionEndResponse;
import com.api.cotella.controller.completion.response.fail.TechQuestionEndFailDTO;
import com.api.cotella.controller.completion.response.success.TechQuestionEndResponseDTO;
import com.api.cotella.repository.question.dto.ModelAnswerDTO;
import com.api.cotella.service.llm.tech.QuestionRecommendationService;
import com.api.cotella.service.llm.tech.dto.RecommendedQuestionPairDTO;
import com.api.cotella.service.llm.tech.dto.TechQuestionAnswerPairDTO;
import com.api.cotella.service.llm.token.LLMTokenManageService;
import com.api.cotella.service.question.InterviewQuestionService;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TechQuestionEndController {

  private final LLMTokenManageService llmTokenManageService;
  private final InterviewQuestionService interviewQuestionService;
  private final QuestionRecommendationService questionRecommendationService;

  public TechQuestionEndController(LLMTokenManageService llmTokenManageService,
      InterviewQuestionService interviewQuestionService,
      QuestionRecommendationService questionRecommendationService) {
    this.llmTokenManageService = llmTokenManageService;
    this.interviewQuestionService = interviewQuestionService;
    this.questionRecommendationService = questionRecommendationService;
  }

  @PostMapping("/users/{userId}/interview-sessions/{interviewSessionId}/tech/completion")
  public TechQuestionEndResponse endTechQuestionSession(
      @PathVariable("userId") Integer userId,
      @PathVariable("interviewSessionId") Integer interviewSessionId,
      @RequestBody List<TechQuestionAnswerPairDTO> techQuestionAnswerPairDTOList) {

    List<Integer> interviewQuestionIds = techQuestionAnswerPairDTOList.stream().map(
        TechQuestionAnswerPairDTO::getInterviewQuestionId).toList();

    List<ModelAnswerDTO> modelAnswerDTOList = interviewQuestionService.giveModelAnswerOfTechQuestions(
        interviewQuestionIds);

    if (llmTokenManageService.hasEnoughTokenOfLLM()) {
      List<RecommendedQuestionPairDTO> recommendedQuestionPairDTOList = questionRecommendationService.recommendRelatedTechQuestions(
          techQuestionAnswerPairDTOList);
      return new TechQuestionEndResponseDTO(modelAnswerDTOList, recommendedQuestionPairDTOList);
    } else {
      return new TechQuestionEndFailDTO(modelAnswerDTOList);
    }
  }
}
