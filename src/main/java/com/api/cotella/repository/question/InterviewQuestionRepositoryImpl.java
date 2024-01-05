package com.api.cotella.repository.question;

import com.querydsl.jpa.impl.JPAQueryFactory;

public class InterviewQuestionRepositoryImpl implements InterviewQuestionRepositoryCustom {

  private final JPAQueryFactory jpaQueryFactory;

  public InterviewQuestionRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
    this.jpaQueryFactory = jpaQueryFactory;
  }
}
