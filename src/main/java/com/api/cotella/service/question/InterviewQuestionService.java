package com.api.cotella.service.question;

import com.api.cotella.model.question.keyword.InterviewKeywordContent;
import com.api.cotella.model.question.topic.InterviewTopic;
import com.api.cotella.repository.question.InterviewQuestionRepository;
import com.api.cotella.repository.question.dto.ModelAnswerDTO;
import com.api.cotella.repository.question.dto.ObjectivesDTO;
import com.api.cotella.repository.session.InterviewSessionRepository;
import com.api.cotella.service.question.dto.FitQuestionStartDTO;
import com.api.cotella.service.question.dto.TechQuestionStartDTO;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class InterviewQuestionService {

  private final InterviewSessionRepository interviewSessionRepository;
  private final InterviewQuestionRepository interviewQuestionRepository;

  public InterviewQuestionService(InterviewSessionRepository interviewSessionRepository,
      InterviewQuestionRepository interviewQuestionRepository) {
    this.interviewSessionRepository = interviewSessionRepository;
    this.interviewQuestionRepository = interviewQuestionRepository;
  }

  public TechQuestionStartDTO giveTechQuestions(Integer userId, InterviewTopic interviewTopic,
      InterviewKeywordContent interviewKeywordContent) {
    return null;
  }

  public FitQuestionStartDTO giveFitQuestions(Integer userId, InterviewTopic interviewTopic,
      InterviewKeywordContent interviewKeywordContent) {
    return null;
  }

  public List<ModelAnswerDTO> giveModelAnswerOfTechQuestions(List<Integer> interviewQuestionIds) {
    return null;
  }

  public List<ObjectivesDTO> giveObjectivesOfQuestions(List<Integer> interviewQuestionIds) {
    return null;
  }
}
