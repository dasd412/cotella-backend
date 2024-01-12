package com.api.cotella.controller.completion.request;

import java.util.List;
import lombok.Data;

@Data
public class FitQuestionEndRequestDTO {

  private Integer userId;

  private Integer interviewSessionId;

  private List<FitQuestionAnswerDTO> fitQuestionAnswerDTOList;

  public FitQuestionEndRequestDTO(Integer userId, Integer interviewSessionId,
      List<FitQuestionAnswerDTO> fitQuestionAnswerDTOList) {
    this.userId = userId;
    this.interviewSessionId = interviewSessionId;
    this.fitQuestionAnswerDTOList = fitQuestionAnswerDTOList;
  }
}
