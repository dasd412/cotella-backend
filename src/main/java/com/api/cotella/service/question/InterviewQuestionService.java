package com.api.cotella.service.question;

import com.api.cotella.common.CommonConstants;
import com.api.cotella.common.filter.InvalidQuestionCountException;
import com.api.cotella.model.question.InterviewQuestion;
import com.api.cotella.model.question.keyword.InterviewKeywordContent;
import com.api.cotella.model.session.InterviewSession;
import com.api.cotella.model.user.InterviewUser;
import com.api.cotella.repository.question.InterviewQuestionRepository;
import com.api.cotella.repository.question.dto.FollowupQuestionDTO;
import com.api.cotella.repository.question.dto.ModelAnswerDTO;
import com.api.cotella.repository.question.dto.ObjectivesDTO;
import com.api.cotella.repository.session.InterviewSessionRepository;
import com.api.cotella.service.question.dto.FitQuestionStartDTO;
import com.api.cotella.service.question.dto.TechQuestionPairDTO;
import com.api.cotella.service.question.dto.TechQuestionStartDTO;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InterviewQuestionService {

  private final InterviewSessionRepository interviewSessionRepository;
  private final InterviewQuestionRepository interviewQuestionRepository;

  public InterviewQuestionService(InterviewSessionRepository interviewSessionRepository,
      InterviewQuestionRepository interviewQuestionRepository) {
    this.interviewSessionRepository = interviewSessionRepository;
    this.interviewQuestionRepository = interviewQuestionRepository;
  }

  @Transactional
  public TechQuestionStartDTO giveRandomTechQuestions(InterviewUser interviewUser,
      InterviewKeywordContent interviewKeywordContent) {

    if (InterviewKeywordContent.ESSENTIAL.getInterviewKeywordId()
        <= interviewKeywordContent.getInterviewKeywordId()
        && interviewKeywordContent.getInterviewKeywordId()
        <= InterviewKeywordContent.VALUES.getInterviewKeywordId()) {
      throw new IllegalArgumentException("This method does not support fit question.");
    }

    Integer interviewSessionId = makeInterviewSession(interviewUser);

    List<InterviewQuestion> initialTechQuestions = giveInitialRandomTechQuestions(
        interviewKeywordContent);

    List<FollowupQuestionDTO> allFollowupQuestionDtoList = giveAllFollowupQuestionsAboutInitialQuestions(
        initialTechQuestions);

    List<FollowupQuestionDTO> chosenRandomFollowupQuestions = chooseOneRandomFollowupQuestionOfEachInitialQuestion(
        allFollowupQuestionDtoList);

    List<TechQuestionPairDTO> techQuestionPairDTOList = mapInitialQuestionAndFollowupQuestion(
        initialTechQuestions, chosenRandomFollowupQuestions);

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

    if (initialTechQuestions.size() != CommonConstants.INITIAL_TECH_QUESTION_NUMBER) {
      throw new InvalidQuestionCountException(
          "Initial questions size must be equal to "
              + CommonConstants.INITIAL_TECH_QUESTION_NUMBER);
    }

    return initialTechQuestions;
  }

  private List<FollowupQuestionDTO> giveAllFollowupQuestionsAboutInitialQuestions(
      List<InterviewQuestion> initialTechQuestions) {
    List<Integer> initialTechQuestionIds = initialTechQuestions.stream()
        .map(InterviewQuestion::getId).toList();

    return this.interviewQuestionRepository.findFollowupQuestionsForAncestors(
        initialTechQuestionIds);
  }

  private List<FollowupQuestionDTO> chooseOneRandomFollowupQuestionOfEachInitialQuestion(
      List<FollowupQuestionDTO> followupQuestionDTOList) {
    Random random = new Random();

    // FollowupQuestionDTO의 리스트를 입력 받아서 같은 ancestor를 가진 DTO들을 그룹화한 후,
    // 각 그룹에서 랜덤하게 한 개의 DTO를 선택하여 새로운 리스트를 만듭니다.
    return followupQuestionDTOList.stream()
        .collect(Collectors.groupingBy(FollowupQuestionDTO::getAncestor))
        .values().stream().map(group -> group.get(random.nextInt(group.size())))
        .collect(Collectors.toList());
  }

  private List<TechQuestionPairDTO> mapInitialQuestionAndFollowupQuestion(
      List<InterviewQuestion> initialTechQuestions,
      List<FollowupQuestionDTO> chosenRandomFollowupQuestions) {

    if (initialTechQuestions.size() != chosenRandomFollowupQuestions.size()) {
      throw new IllegalArgumentException(
          "Initial questions size must be equal to chosenRandomFollowupQuestions");
    }

    // (초기 질문 (id=1), 꼬리 질문 (ancestor=1))과 같이 매핑하기 위해 정렬합니다.
    List<InterviewQuestion> sortedInitialQuestions = initialTechQuestions.stream()
        .sorted(Comparator.comparing(InterviewQuestion::getId))
        .toList();

    List<FollowupQuestionDTO> sortedFollowupQuestions = chosenRandomFollowupQuestions.stream()
        .sorted(Comparator.comparing(FollowupQuestionDTO::getAncestor))
        .toList();

    return IntStream.range(0, CommonConstants.INITIAL_TECH_QUESTION_NUMBER)
        .mapToObj(
            i -> new TechQuestionPairDTO(sortedInitialQuestions.get(i),
                sortedFollowupQuestions.get(i)))
        .collect(Collectors.toList());
  }

  @Transactional
  public FitQuestionStartDTO giveRandomFitQuestions(InterviewUser interviewUser,
      InterviewKeywordContent interviewKeywordContent) {

    if (interviewKeywordContent.getInterviewKeywordId()
        == InterviewKeywordContent.ESSENTIAL.getInterviewKeywordId()) {
      throw new IllegalArgumentException(
          "This method supports fit keyword except essential keyword.");
    }

    if (InterviewKeywordContent.DB.getInterviewKeywordId()
        <= interviewKeywordContent.getInterviewKeywordId()
        && interviewKeywordContent.getInterviewKeywordId()
        <= InterviewKeywordContent.SPRING.getInterviewKeywordId()) {
      throw new IllegalArgumentException("This method does not support tech question.");
    }

    Integer interviewSessionId = makeInterviewSession(interviewUser);

    List<InterviewQuestion> fitQuestions = interviewQuestionRepository.findRandomFitQuestions(
        interviewKeywordContent);

    return new FitQuestionStartDTO(interviewSessionId, fitQuestions);
  }

  @Transactional(readOnly = true)
  public List<ModelAnswerDTO> giveModelAnswerOfTechQuestions(List<Integer> interviewQuestionIds) {
    return interviewQuestionRepository.findModelAnswersOfQuestions(interviewQuestionIds);
  }

  @Transactional(readOnly = true)
  public List<ObjectivesDTO> giveObjectivesOfQuestions(List<Integer> interviewQuestionIds) {
    return interviewQuestionRepository.findObjectivesOfQuestions(interviewQuestionIds);
  }
}
