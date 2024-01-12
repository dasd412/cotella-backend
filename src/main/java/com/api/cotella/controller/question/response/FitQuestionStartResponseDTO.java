package com.api.cotella.controller.question.response;

import com.api.cotella.service.question.dto.FitQuestionStartDTO;
import lombok.Data;

@Data
public class FitQuestionStartResponseDTO {

  private FitQuestionStartDTO fitQuestionStartDTO;

  public FitQuestionStartResponseDTO(FitQuestionStartDTO fitQuestionStartDTO) {
    this.fitQuestionStartDTO = fitQuestionStartDTO;
  }
}
