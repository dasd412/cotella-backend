package com.api.cotella.common.util;

import com.api.cotella.service.llm.tech.dto.TechQuestionAnswerPairDTO;

public class PromptUtils {

  public static String loadPromptForQuestionRecommendation(){
    return null;
  }

  public static String makeInputPromptForQuestionRecommendation(TechQuestionAnswerPairDTO dto) {
    String questionContent = dto.getQuestionContent();
    String answerOfUser = dto.getAnswerOfUser();

    return null;
  }
}
