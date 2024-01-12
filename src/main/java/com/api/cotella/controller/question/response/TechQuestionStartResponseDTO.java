package com.api.cotella.controller.question.response;

import com.api.cotella.service.question.dto.TechQuestionStartDTO;
import lombok.Data;

@Data
public class TechQuestionStartResponseDTO {

  private TechQuestionStartDTO techQuestionStartDTO;

  public TechQuestionStartResponseDTO(TechQuestionStartDTO techQuestionStartDTO) {
    this.techQuestionStartDTO = techQuestionStartDTO;
  }
}
