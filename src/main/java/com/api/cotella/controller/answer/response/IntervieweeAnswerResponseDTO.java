package com.api.cotella.controller.answer.response;

import lombok.Data;

@Data
public class IntervieweeAnswerResponseDTO {

  private Integer answerId;

  public IntervieweeAnswerResponseDTO(Integer answerId) {
    this.answerId = answerId;
  }
}
