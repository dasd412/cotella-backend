package com.api.cotella.service.llm;

import com.api.cotella.common.config.GPTConfig;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
@Profile({"local", "dev", "prod"})
@Service
public class LLMCallService {

  private final OpenAiService openAiService;

  public LLMCallService(OpenAiService openAiService) {
    this.openAiService = openAiService;
  }

  public ChatCompletionResult generate(List<ChatMessage> chatMessages) {
    ChatCompletionRequest chatCompletionResult = ChatCompletionRequest.builder()
        .messages(chatMessages)
        .temperature(GPTConfig.TEMPERATURE)
        .model(GPTConfig.MODEL)
        .build();

    return openAiService.createChatCompletion(chatCompletionResult);
  }
}
