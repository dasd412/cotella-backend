package com.api.cotella.common.util;

import com.api.cotella.service.llm.tech.dto.TechQuestionAnswerPairDTO;

public class PromptUtils {

  public static String loadPromptForQuestionRecommendation() {
    return """
        단계별로 차근 차근 생각해봅시다.
                
        [상황]
        당신은 면접 질문과 답변을 보고, 이와 관련된 연관 질문을 3개 생성해야 합니다.
                
        [결과 양식]
        아래 양식처럼 연관 질문을 3개 생성해주세요.
                
        1. 연관 질문 1
        2. 연관 질문 2
        3. 연관 질문 3
        """;
  }

  public static String makeInputPromptForQuestionRecommendation(TechQuestionAnswerPairDTO dto) {
    String questionContent = dto.getQuestionContent();
    String answerOfUser = dto.getAnswerOfUser();

    return String.format(""" 
        [질문 내용]
        %s
                
        [답변]
        %s
                
        양식을 지켜서 연관 질문을 생성하세요.""", questionContent, answerOfUser);
  }
}
