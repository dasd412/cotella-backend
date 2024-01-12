package com.api.cotella.controller.answer.request;

import lombok.Data;

@Data
public class IntervieweeAnswerRequestDTO {

  private Integer interviewSessionId;

  private Integer questionId;

  private String answerContent;

  public IntervieweeAnswerRequestDTO(Integer interviewSessionId, Integer questionId,
      String answerContent) {
    this.interviewSessionId = interviewSessionId;
    this.questionId = questionId;
    this.answerContent = answerContent;
  }
}
