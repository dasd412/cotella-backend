package com.api.cotella.model.question;

import lombok.Getter;

@Getter
public enum InterviewKeywordContent {

  DB(1),

  DATA_STRUCTURE(2),

  JAVA(3),

  NETWORK(4),

  OS(5),

  SPRING(6),

  ESSENTIAL(7),

  EXPERIENCE(8),

  PERSONALITY(9),

  PRESSURE(10),

  SITUATION(11),

  VALUES(12);

  private final int interviewKeywordId;

  InterviewKeywordContent(int interviewKeywordId) {
    this.interviewKeywordId = interviewKeywordId;
  }
}
