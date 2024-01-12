package com.api.cotella.controller.completion.response.fail;

import com.api.cotella.repository.question.dto.ModelAnswerDTO;
import java.util.List;
import lombok.Data;

@Data
public class TechQuestionEndFailDTO {

  private List<ModelAnswerDTO> modelAnswerDTOList;

  public TechQuestionEndFailDTO(List<ModelAnswerDTO> modelAnswerDTOList) {
    this.modelAnswerDTOList = modelAnswerDTOList;
  }
}
