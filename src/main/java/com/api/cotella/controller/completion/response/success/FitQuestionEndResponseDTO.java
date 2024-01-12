package com.api.cotella.controller.completion.response.success;

import com.api.cotella.service.llm.fit.dto.FitInterviewEditingResultDTO;
import java.util.List;
import lombok.Data;

@Data
public class FitQuestionEndResponseDTO {

  private List<FitInterviewEditingResultDTO> fitInterviewEditingResultDTOList;

  public FitQuestionEndResponseDTO(
      List<FitInterviewEditingResultDTO> fitInterviewEditingResultDTOList) {
    this.fitInterviewEditingResultDTOList = fitInterviewEditingResultDTOList;
  }
}
