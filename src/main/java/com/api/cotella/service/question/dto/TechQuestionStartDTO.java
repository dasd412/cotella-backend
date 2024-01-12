package com.api.cotella.service.question.dto;

import java.util.List;
import lombok.Data;

@Data
public class TechQuestionStartDTO {

  private Integer interviewSessionId;

  private List<TechQuestionPairDTO> techQuestionPairDTOList;

  public TechQuestionStartDTO(Integer interviewSessionId,
      List<TechQuestionPairDTO> techQuestionPairDTOList) {
    this.interviewSessionId = interviewSessionId;
    this.techQuestionPairDTOList = techQuestionPairDTOList;
  }
}
