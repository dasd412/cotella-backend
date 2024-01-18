package com.api.cotella.repository.question;

import com.api.cotella.model.question.InterviewQuestion;
import com.api.cotella.model.question.keyword.InterviewKeywordContent;
import com.api.cotella.repository.question.dto.FollowupQuestionDTO;
import com.api.cotella.repository.question.dto.ModelAnswerDTO;
import com.api.cotella.repository.question.dto.ObjectivesDTO;
import java.util.List;

public interface InterviewQuestionRepositoryCustom {

  List<FollowupQuestionDTO> findFollowupQuestionsForAncestors(List<Integer> interviewQuestionIds);

  List<InterviewQuestion> findRandomFitQuestions(
      InterviewKeywordContent interviewKeywordContent) throws IllegalArgumentException;

  List<ModelAnswerDTO> findModelAnswersOfQuestions(List<Integer> interviewQuestionIds);

  List<ObjectivesDTO> findObjectivesOfQuestions(List<Integer> interviewQuestionIds);
}
