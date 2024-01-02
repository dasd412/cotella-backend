package com.api.cotella.repository.user;

import com.api.cotella.model.user.QInterviewUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.api.cotella.model.user.InterviewUser;
import java.util.Optional;

public class InterviewUserRepositoryImpl implements InterviewUserRepositoryCustom {

  private final JPAQueryFactory jpaQueryFactory;

  public InterviewUserRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
    this.jpaQueryFactory = jpaQueryFactory;
  }

  @Override
  public Optional<InterviewUser> findInterviewUserByName(String username) {
    return Optional.ofNullable(jpaQueryFactory.selectFrom(QInterviewUser.interviewUser)
        .where(QInterviewUser.interviewUser.name.eq(username))
        .fetchOne());
  }

  @Override
  public Boolean existName(String username) {
    Integer fetchFirst = jpaQueryFactory
        .selectOne()
        .from(QInterviewUser.interviewUser)
        .where(QInterviewUser.interviewUser.name.eq(username))
        .fetchFirst();/* 값이 없으면 0이 아니라 null 반환. */

    return fetchFirst != null;
  }

  @Override
  public Boolean existEmail(String email, String provider) {
    Integer fetchFirst;
    if (provider == null) {
      fetchFirst = jpaQueryFactory
          .selectOne()
          .from(QInterviewUser.interviewUser)
          .where(QInterviewUser.interviewUser.email.eq(email))
          .fetchFirst();
    } else {
      fetchFirst = jpaQueryFactory
          .selectOne()
          .from(QInterviewUser.interviewUser)
          .where(QInterviewUser.interviewUser.email.eq(email).and(QInterviewUser.interviewUser.provider.eq(provider)))
          .fetchFirst();
    }
    return fetchFirst != null;
  }
}
