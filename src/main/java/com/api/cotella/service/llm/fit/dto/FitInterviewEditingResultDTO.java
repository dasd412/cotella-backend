package com.api.cotella.service.llm.fit.dto;

import lombok.Data;

@Data
public class FitInterviewEditingResultDTO {

  private Integer questionId;

  private String editingByLLM;

  public FitInterviewEditingResultDTO(Integer questionId, String editingByLLM) {
    this.questionId = questionId;
    this.editingByLLM = editingByLLM;
  }
}
