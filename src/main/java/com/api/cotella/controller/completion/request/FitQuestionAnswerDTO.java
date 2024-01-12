package com.api.cotella.controller.completion.request;

import lombok.Builder;
import lombok.Data;

@Data
public class FitQuestionAnswerDTO {

  private Integer interviewQuestionId;

  private String questionContent;

  private Integer intervieweeAnswerId;

  private String answerOfUser;

  @Builder
  public FitQuestionAnswerDTO(Integer interviewQuestionId, String questionContent,
      Integer intervieweeAnswerId, String answerOfUser) {
    this.interviewQuestionId = interviewQuestionId;
    this.questionContent = questionContent;
    this.intervieweeAnswerId = intervieweeAnswerId;
    this.answerOfUser = answerOfUser;
  }
}
