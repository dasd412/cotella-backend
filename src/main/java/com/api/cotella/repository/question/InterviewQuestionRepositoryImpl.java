package com.api.cotella.repository.question;

import com.api.cotella.model.question.InterviewQuestion;
import com.api.cotella.model.question.QInterviewQuestion;
import com.api.cotella.model.question.QQuestionMetaData;
import com.api.cotella.model.question.keyword.InterviewKeywordContent;
import com.api.cotella.repository.question.dto.ModelAnswerDTO;
import com.api.cotella.repository.question.dto.ObjectivesDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;

public class InterviewQuestionRepositoryImpl implements InterviewQuestionRepositoryCustom {

  private final JPAQueryFactory jpaQueryFactory;

  private final QQuestionMetaData questionMetaData = QQuestionMetaData.questionMetaData;
  private final QInterviewQuestion interviewQuestion = QInterviewQuestion.interviewQuestion;

  public InterviewQuestionRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
    this.jpaQueryFactory = jpaQueryFactory;
  }

  @Override
  public List<InterviewQuestion> findRandomTechQuestions(
      InterviewKeywordContent interviewKeywordContent) {
    /*
    1. 초기 질문 랜덤 5개 조회

    select * from interview_question as iq
    join question_closure_table as qct on iq.interview_question_id=qct.descendant
    where qct.ancestor  in (select distinct qct.ancestor from question_closure_table as qct where qct.ancestor!=qct.descendant and qct.depth=1)
    and qct.depth=0 and iq.interview_keyword_id=1 order by rand() limit 5;
     */

    /*
    2. 각 초기 질문에 대해 꼬리 질문 1개씩 랜덤 조회

    (select * from interview_question as iq
    join question_closure_table as qct on iq.interview_question_id=qct.descendant
    where qct.ancestor =1 and qct.depth=1 order by rand() limit 1)
    UNION ALL
    (select * from interview_question as iq
    join question_closure_table as qct on iq.interview_question_id=qct.descendant
    where qct.ancestor =2 and qct.depth=1 order by rand() limit 1)
    UNION ALL
    (select * from interview_question as iq
    join question_closure_table as qct on iq.interview_question_id=qct.descendant
    where qct.ancestor =3 and qct.depth=1 order by rand() limit 1)
    UNION ALL
    (select * from interview_question as iq
    join question_closure_table as qct on iq.interview_question_id=qct.descendant
    where qct.ancestor =5 and qct.depth=1 order by rand() limit 1)
    UNION ALL
    (select * from interview_question as iq
    join question_closure_table as qct on iq.interview_question_id=qct.descendant
    where qct.ancestor =6 and qct.depth=1 order by rand() limit 1);
     */
    return null;
  }

  @Override
  public List<InterviewQuestion> findRandomFitQuestions(
      InterviewKeywordContent interviewKeywordContent) {
    /*
    필수 질문 5개 조회 + 다른 인성 질문 랜덤 5개
    
    select * from interview_question where interview_keyword_id=7 UNION ALL
    (select * from interview_question  where interview_keyword_id=11 order by rand() limit 5);
     */
    return null;
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
