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

  /*
   * querydsl 에선 exists 사용시 count()를 사용하므로 총 몇건인 지 확인하기 위해 전체를 확인하는 추가 작업이 필요하다.
   * 따라서 Querydsl 이 기본적으로 제공하는 exists 는 성능 상 좋지 않다.
   * 대신 fetchFirst()를 사용하여 limit(1)의 효과를 낼 수 있도록 하면 성능이 개선된다.
   */
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
