package com.api.cotella.service.question.dto;

import com.api.cotella.model.question.InterviewQuestion;
import com.api.cotella.repository.question.dto.FollowupQuestionDTO;
import lombok.Data;

@Data
public class TechQuestionPairDTO {

  private InterviewQuestion initialTechQuestion;

  private FollowupQuestionDTO followupTechQuestion;

  public TechQuestionPairDTO(InterviewQuestion initialTechQuestion,
      FollowupQuestionDTO followupTechQuestion) {
    this.initialTechQuestion = initialTechQuestion;
    this.followupTechQuestion = followupTechQuestion;
  }
}
