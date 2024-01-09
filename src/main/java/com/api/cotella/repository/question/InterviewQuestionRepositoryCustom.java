package com.api.cotella.repository.question;

import com.api.cotella.model.question.InterviewQuestion;
import com.api.cotella.model.question.keyword.InterviewKeywordContent;
import com.api.cotella.repository.question.dto.ModelAnswerDTO;
import com.api.cotella.repository.question.dto.ObjectivesDTO;
import java.util.List;

public interface InterviewQuestionRepositoryCustom {

  List<InterviewQuestion> findRandomTechQuestions(
      InterviewKeywordContent interviewKeywordContent);

  List<InterviewQuestion> findRandomFitQuestions(
      InterviewKeywordContent interviewKeywordContent);

  List<InterviewQuestion> findRandomTechQuestionsWithKeyword(
      InterviewKeywordContent interviewKeywordContent);

  List<InterviewQuestion> findRandomTechQuestions();

  List<InterviewQuestion> findRandomFitQuestionsWithKeyword(
      InterviewKeywordContent interviewKeywordContent);

  List<InterviewQuestion> findRandomFitQuestions();

  List<ModelAnswerDTO> findModelAnswersOfQuestions(List<Integer> interviewQuestionIds);

  List<ObjectivesDTO> findObjectivesOfQuestions(List<Integer> interviewQuestionIds);
}
