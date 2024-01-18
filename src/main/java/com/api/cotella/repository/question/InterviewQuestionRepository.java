package com.api.cotella.repository.question;

import com.api.cotella.model.question.InterviewQuestion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewQuestionRepository extends JpaRepository<InterviewQuestion, Integer>,
    InterviewQuestionRepositoryCustom {

  /*
  초기 질문 랜덤 5개 조회
   */
  @Query(value = ""
      + "SELECT iq.interview_question_id,iq.question_content,iq.interview_keyword_id, iq.created_at, iq.updated_at, iq.rand_id FROM interview_question AS iq "
      + "JOIN question_closure_table AS qct ON iq.interview_question_id = qct.descendant "
      + "WHERE qct.ancestor IN "
      + "(SELECT DISTINCT qct.ancestor FROM question_closure_table AS qct "
      + " WHERE qct.ancestor != qct.descendant AND qct.depth = 1)"
      + "AND qct.depth = 0 AND iq.interview_keyword_id = :keyword_id "
      + "ORDER BY rand() LIMIT 5", nativeQuery = true)
  List<InterviewQuestion> findInitialRandomTechQuestions(@Param("keyword_id") Integer keywordId);
}
