package com.api.cotella.service.question.dto;

import com.api.cotella.model.question.InterviewQuestion;
import java.util.List;
import lombok.Data;

@Data
public class FitQuestionStartDTO {

  private Integer interviewSessionId;

  private List<InterviewQuestion> fitInterviewQuestions;

  public FitQuestionStartDTO(Integer interviewSessionId,
      List<InterviewQuestion> fitInterviewQuestions) {
    this.interviewSessionId = interviewSessionId;
    this.fitInterviewQuestions = fitInterviewQuestions;
  }
}
