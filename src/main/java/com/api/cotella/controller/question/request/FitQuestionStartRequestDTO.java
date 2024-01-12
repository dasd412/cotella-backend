package com.api.cotella.controller.question.request;

import com.api.cotella.model.question.keyword.InterviewKeywordContent;
import com.api.cotella.model.question.topic.InterviewTopic;
import lombok.Data;

@Data
public class FitQuestionStartRequestDTO {

  private Integer userId;

  private InterviewTopic interviewTopic;

  private InterviewKeywordContent interviewKeywordContent;

  public FitQuestionStartRequestDTO(Integer userId, InterviewTopic interviewTopic,
      InterviewKeywordContent interviewKeywordContent) {
    this.userId = userId;
    this.interviewTopic = interviewTopic;
    this.interviewKeywordContent = interviewKeywordContent;
  }
}
