package com.api.cotella.service.question.dto;

import com.api.cotella.model.question.InterviewQuestion;
import lombok.Data;

@Data
public class TechQuestionPairDTO {

  private InterviewQuestion initialTechQuestion;

  private InterviewQuestion followupTechQuestion;

  public TechQuestionPairDTO(InterviewQuestion initialTechQuestion,
      InterviewQuestion followupTechQuestion) {
    this.initialTechQuestion = initialTechQuestion;
    this.followupTechQuestion = followupTechQuestion;
  }
}
