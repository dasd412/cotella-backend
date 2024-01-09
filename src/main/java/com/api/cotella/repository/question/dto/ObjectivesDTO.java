package com.api.cotella.repository.question.dto;

import lombok.Data;

@Data
public class ObjectivesDTO {

  private Integer interviewQuestionId;

  private String objectives;

  public ObjectivesDTO(Integer interviewQuestionId, String objectives) {
    this.interviewQuestionId = interviewQuestionId;
    this.objectives = objectives;
  }
}
