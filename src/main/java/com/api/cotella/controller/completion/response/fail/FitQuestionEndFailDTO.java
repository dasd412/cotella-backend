package com.api.cotella.controller.completion.response.fail;

import com.api.cotella.repository.question.dto.ObjectivesDTO;
import java.util.List;
import lombok.Data;

@Data
public class FitQuestionEndFailDTO {

  private List<ObjectivesDTO> objectivesDTOList;

  public FitQuestionEndFailDTO(List<ObjectivesDTO> objectivesDTOList) {
    this.objectivesDTOList = objectivesDTOList;
  }
}
