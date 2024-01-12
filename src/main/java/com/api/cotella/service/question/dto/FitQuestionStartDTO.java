package com.api.cotella.service.question.dto;

import com.api.cotella.model.session.InterviewSession;
import java.util.List;
import lombok.Data;

@Data
public class FitQuestionStartDTO {

  private Integer interviewSessionId;

  private List<InterviewSession> fitInterviewQuestions;

  public FitQuestionStartDTO(Integer interviewSessionId,
      List<InterviewSession> fitInterviewQuestions) {
    this.interviewSessionId = interviewSessionId;
    this.fitInterviewQuestions = fitInterviewQuestions;
  }
}
