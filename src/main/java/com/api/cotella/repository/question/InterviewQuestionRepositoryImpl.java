package com.api.cotella.repository.question;

import com.api.cotella.model.question.InterviewQuestion;
import com.api.cotella.model.question.QInterviewQuestion;
import com.api.cotella.model.question.QQuestionMetaData;
import com.api.cotella.model.question.keyword.InterviewKeywordContent;
import com.api.cotella.repository.question.dto.ModelAnswerDTO;
import com.api.cotella.repository.question.dto.ObjectivesDTO;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;

public class InterviewQuestionRepositoryImpl implements InterviewQuestionRepositoryCustom {

  private final JPAQueryFactory jpaQueryFactory;

  private final QQuestionMetaData questionMetaData = QQuestionMetaData.questionMetaData;
  private final QInterviewQuestion interviewQuestion = QInterviewQuestion.interviewQuestion;

  public InterviewQuestionRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
    this.jpaQueryFactory = jpaQueryFactory;
  }

  @Override
  public List<InterviewQuestion> findRandomFitQuestions(
      InterviewKeywordContent interviewKeywordContent) {
    /*
    필수 질문 5개 조회 + 다른 인성 질문 랜덤 5개
    
    select * from interview_question where interview_keyword_id=7 UNION ALL
    (select * from interview_question  where interview_keyword_id=11 order by rand() limit 5);
     */

    int essentialKeywordId = InterviewKeywordContent.ESSENTIAL.getInterviewKeywordId();

    List<InterviewQuestion> firstQuery = jpaQueryFactory.selectFrom(interviewQuestion)
        .where(interviewQuestion.id.eq(essentialKeywordId))
        .fetch();

    List<InterviewQuestion> result = new ArrayList<>(firstQuery);

    List<InterviewQuestion> secondQuery = jpaQueryFactory.selectFrom(interviewQuestion)
        .where(interviewQuestion.id.eq(interviewKeywordContent.getInterviewKeywordId()))
        .orderBy(makeRandom())
        .limit(5).fetch();

    result.addAll(secondQuery);

    return result;
  }

  private static OrderSpecifier<Double> makeRandom() {
    return Expressions.numberTemplate(Double.class, "function('rand')").asc();
  }

  @Override
  public List<ModelAnswerDTO> findModelAnswersOfQuestions(List<Integer> interviewQuestionIds) {
    /*
      select q.interview_question_id, q.model_answer_content from question_metadata q
      inner join interview_question i
      on q.interview_question_id=i.interview_question_id
      where i.interview_question_id in (2,5,6,7,10);
     */
    return jpaQueryFactory
        .select(Projections.constructor(
            ModelAnswerDTO.class, questionMetaData.interviewQuestion.id,
            questionMetaData.modelAnswerContent))
        .from(questionMetaData)
        .innerJoin(interviewQuestion)
        .on(questionMetaData.interviewQuestion.eq(interviewQuestion))
        .where(interviewQuestion.id.in(interviewQuestionIds))
        .fetch();
  }

  @Override
  public List<ObjectivesDTO> findObjectivesOfQuestions(List<Integer> interviewQuestionIds) {
    /*
      select q.interview_question_id, q.objectives from question_metadata q
      inner join interview_question i
      on q.interview_question_id=i.interview_question_id
      where i.interview_question_id in (177,182,185);
    */
    return jpaQueryFactory
        .select(Projections.constructor(
            ObjectivesDTO.class, questionMetaData.interviewQuestion.id,
            questionMetaData.objectives))
        .from(questionMetaData)
        .innerJoin(interviewQuestion)
        .on(questionMetaData.interviewQuestion.eq(interviewQuestion))
        .where(interviewQuestion.id.in(interviewQuestionIds))
        .fetch();
  }
}
