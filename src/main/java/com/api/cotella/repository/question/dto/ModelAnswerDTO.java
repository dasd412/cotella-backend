package com.api.cotella.repository.question.dto;

import lombok.Data;

@Data
public class ModelAnswerDTO {

  private Integer interviewQuestionId;

  private String modelAnswerContent;

  public ModelAnswerDTO(Integer interviewQuestionId, String modelAnswerContent) {
    this.interviewQuestionId = interviewQuestionId;
    this.modelAnswerContent = modelAnswerContent;
  }
}
