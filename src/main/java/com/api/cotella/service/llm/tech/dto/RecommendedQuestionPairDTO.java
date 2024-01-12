package com.api.cotella.service.llm.tech.dto;

import lombok.Data;

@Data
public class RecommendedQuestionPairDTO {

  private Integer questionId;

  private String relatedQuestionByLLMRecommendation;

  public RecommendedQuestionPairDTO(Integer questionId, String relatedQuestionByLLMRecommendation) {
    this.questionId = questionId;
    this.relatedQuestionByLLMRecommendation = relatedQuestionByLLMRecommendation;
  }
}
