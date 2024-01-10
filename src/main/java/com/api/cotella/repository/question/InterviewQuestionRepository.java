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
      + "SELECT * FROM interview_question AS iq "
      + "JOIN question_closure_table AS qct ON iq.interview_question_id = qct.descendant "
      + "WHERE qct.ancestor IN "
      + "(SELECT DISTINCT qct.ancestor FROM question_closure_table AS qct "
      + " WHERE qct.ancestor != qct.descendant AND qct.depth = 1)"
      + "AND qct.depth = 0 AND iq.interview_keyword_id = :keyword_id "
      + "ORDER BY rand() LIMIT 5", nativeQuery = true)
  List<InterviewQuestion> findInitialRandomTechQuestions(@Param("keyword_id") Long keywordId);

  /*
  2. 각 초기 질문에 대해 꼬리 질문 1개씩 랜덤 조회
   */
  @Query(value = ""
      + "(SELECT * FROM interview_question AS iq "
      + "JOIN question_closure_table AS qct ON iq.interview_question_id = qct.descendant "
      + "WHERE qct.ancestor = :ancestor_id1 AND qct.depth = 1 ORDER BY rand() LIMIT 1) "
      + "UNION ALL "
      + "(SELECT * FROM interview_question AS iq "
      + "JOIN question_closure_table AS qct ON iq.interview_question_id = qct.descendant "
      + "WHERE qct.ancestor = :ancestor_id2 AND qct.depth = 1 ORDER BY rand() LIMIT 1) "
      + "UNION ALL "
      + "(SELECT * FROM interview_question AS iq "
      + "JOIN question_closure_table AS qct ON iq.interview_question_id = qct.descendant "
      + "WHERE qct.ancestor = :ancestor_id3 AND qct.depth = 1 ORDER BY rand() LIMIT 1) "
      + "UNION ALL "
      + "(SELECT * FROM interview_question AS iq "
      + "JOIN question_closure_table AS qct ON iq.interview_question_id = qct.descendant "
      + "WHERE qct.ancestor = :ancestor_id4 AND qct.depth = 1 ORDER BY rand() LIMIT 1)"
      + "UNION ALL "
      + "(SELECT * FROM interview_question AS iq "
      + "JOIN question_closure_table AS qct ON iq.interview_question_id = qct.descendant "
      + "WHERE qct.ancestor = :ancestor_id5 AND qct.depth = 1 ORDER BY rand() LIMIT 1)", nativeQuery = true)
  List<InterviewQuestion> findTailQuestionsForAncestors(@Param("ancestor_id1") Long ancestorId1, @Param("ancestor_id2") Long ancestorId2,
      @Param("ancestor_id3") Long ancestorId3, @Param("ancestor_id4") Long ancestorId4, @Param("ancestor_id5") Long ancestorId5);
}
