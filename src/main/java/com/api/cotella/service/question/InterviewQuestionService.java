package com.api.cotella.service.question;

import com.api.cotella.common.filter.InvalidQuestionCountException;
import com.api.cotella.model.question.InterviewQuestion;
import com.api.cotella.model.question.keyword.InterviewKeywordContent;
import com.api.cotella.model.question.topic.InterviewTopic;
import com.api.cotella.model.session.InterviewSession;
import com.api.cotella.model.user.InterviewUser;
import com.api.cotella.repository.question.InterviewQuestionRepository;
import com.api.cotella.repository.question.dto.ModelAnswerDTO;
import com.api.cotella.repository.question.dto.ObjectivesDTO;
import com.api.cotella.repository.session.InterviewSessionRepository;
import com.api.cotella.service.question.dto.FitQuestionStartDTO;
import com.api.cotella.service.question.dto.TechQuestionPairDTO;
import com.api.cotella.service.question.dto.TechQuestionStartDTO;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;

@Service
public class InterviewQuestionService {

  private final InterviewSessionRepository interviewSessionRepository;
  private final InterviewQuestionRepository interviewQuestionRepository;
  private final static Integer QUESTION_SIZE = 5;

  public InterviewQuestionService(InterviewSessionRepository interviewSessionRepository,
      InterviewQuestionRepository interviewQuestionRepository) {
    this.interviewSessionRepository = interviewSessionRepository;
    this.interviewQuestionRepository = interviewQuestionRepository;
  }

  @Transactional
  public TechQuestionStartDTO giveTechQuestions(InterviewUser interviewUser,
      InterviewKeywordContent interviewKeywordContent) {

    Integer interviewSessionId = makeInterviewSession(interviewUser);

    List<InterviewQuestion> initialTechQuestions = giveInitialRandomTechQuestions(
        interviewKeywordContent);

    List<InterviewQuestion> followupQuestions = giveFollowupQuestionsAboutInitialQuestions(
        initialTechQuestions);

    List<TechQuestionPairDTO> techQuestionPairDTOList = IntStream.range(0, QUESTION_SIZE)
        .mapToObj(
            i -> new TechQuestionPairDTO(initialTechQuestions.get(i), followupQuestions.get(i)))
        .collect(Collectors.toList());

    return new TechQuestionStartDTO(interviewSessionId, techQuestionPairDTOList);
  }

  private Integer makeInterviewSession(InterviewUser interviewUser) {
    InterviewSession interviewSession = new InterviewSession(interviewUser);

    return this.interviewSessionRepository.save(interviewSession).getId();
  }

  private List<InterviewQuestion> giveInitialRandomTechQuestions(
      InterviewKeywordContent interviewKeywordContent) {
    List<InterviewQuestion> initialTechQuestions =
        this.interviewQuestionRepository.findInitialRandomTechQuestions(
            interviewKeywordContent.getInterviewKeywordId());

    if (initialTechQuestions.size() != QUESTION_SIZE) {
      throw new InvalidQuestionCountException(
          "Initial questions size must be equal to " + QUESTION_SIZE);
    }

    return initialTechQuestions;
  }

  private List<InterviewQuestion> giveFollowupQuestionsAboutInitialQuestions(
      List<InterviewQuestion> initialTechQuestions) {

    List<Integer>initialTechQuestionIds=initialTechQuestions.stream().map(InterviewQuestion::getId).toList();

    List<InterviewQuestion> followupQuestions =
        this.interviewQuestionRepository.findFollowupQuestionsForAncestors(initialTechQuestionIds);

    if (followupQuestions.size() != QUESTION_SIZE) {
      throw new InvalidQuestionCountException(
          "Followup questions size must be equal to " + QUESTION_SIZE);
    }

    return followupQuestions;
  }

  public FitQuestionStartDTO giveFitQuestions(InterviewUser interviewUser,
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
