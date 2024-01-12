package com.api.cotella.controller.completion.request;

import java.util.List;
import lombok.Data;

@Data
public class TechQuestionEndRequestDTO {

  private List<TechQuestionAnswerPairDTO> techQuestionAnswerPairDTOList;

  public TechQuestionEndRequestDTO(List<TechQuestionAnswerPairDTO> techQuestionAnswerPairDTOList) {
    this.techQuestionAnswerPairDTOList = techQuestionAnswerPairDTOList;
  }
}
