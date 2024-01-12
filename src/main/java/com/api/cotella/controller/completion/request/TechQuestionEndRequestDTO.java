package com.api.cotella.controller.completion.request;

import com.api.cotella.service.llm.tech.dto.TechQuestionAnswerPairDTO;
import java.util.List;
import lombok.Data;

@Data
public class TechQuestionEndRequestDTO {

  private Integer userId;

  private Integer interviewSessionId;

  private List<TechQuestionAnswerPairDTO> techQuestionAnswerPairDTOList;

  public TechQuestionEndRequestDTO(Integer userId, Integer interviewSessionId,
      List<TechQuestionAnswerPairDTO> techQuestionAnswerPairDTOList) {
    this.userId = userId;
    this.interviewSessionId = interviewSessionId;
    this.techQuestionAnswerPairDTOList = techQuestionAnswerPairDTOList;
  }
}
