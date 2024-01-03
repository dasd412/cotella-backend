package com.api.cotella.model.question.topic;

import lombok.Getter;

@Getter
public enum InterviewCategory {

  TECH(1),

  FIT(2);

  private final int interviewCategoryId;

  InterviewCategory(int interviewCategoryId) {
    this.interviewCategoryId = interviewCategoryId;
  }
}
