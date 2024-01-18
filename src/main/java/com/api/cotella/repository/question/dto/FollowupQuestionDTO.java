package com.api.cotella.repository.question.dto;

import lombok.Data;

@Data
public class FollowupQuestionDTO {

  private Integer ancestor;

  private Integer questionId;

  private String questionContent;

  public FollowupQuestionDTO(Integer ancestor, Integer questionId, String questionContent) {
    this.ancestor = ancestor;
    this.questionId = questionId;
    this.questionContent = questionContent;
  }
}
