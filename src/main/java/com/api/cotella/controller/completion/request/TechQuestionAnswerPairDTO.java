package com.api.cotella.controller.completion.request;

import lombok.Data;

@Data
public class TechQuestionAnswerPairDTO {

  private Integer interviewQuestionId;

  private String questionContent;

  private String answerOfUser;

  public TechQuestionAnswerPairDTO(Integer interviewQuestionId, String questionContent,
      String answerOfUser) {
    this.interviewQuestionId = interviewQuestionId;
    this.questionContent = questionContent;
    this.answerOfUser = answerOfUser;
  }
}
