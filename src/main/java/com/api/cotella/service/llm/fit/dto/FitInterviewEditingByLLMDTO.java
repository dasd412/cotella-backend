package com.api.cotella.service.llm.fit.dto;

import lombok.Data;

@Data
public class FitInterviewEditingByLLMDTO {

  private Integer questionId;

  private Integer answerId;

  private String answerOfUser;

  private String objectives;

  public FitInterviewEditingByLLMDTO(Integer questionId, Integer answerId, String answerOfUser,
      String objectives) {
    this.questionId = questionId;
    this.answerId = answerId;
    this.answerOfUser = answerOfUser;
    this.objectives = objectives;
  }
}
