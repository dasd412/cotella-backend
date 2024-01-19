package com.api.cotella.service.answer;

import static org.junit.jupiter.api.Assertions.*;

import com.api.cotella.config.JpaTestConfiguration;
import com.api.cotella.controller.answer.request.IntervieweeAnswerRequestDTO;
import com.api.cotella.model.question.keyword.InterviewKeywordContent;
import com.api.cotella.model.user.InterviewUser;
import com.api.cotella.repository.answer.IntervieweeAnswerRepository;
import com.api.cotella.repository.user.InterviewUserRepository;
import com.api.cotella.service.question.InterviewQuestionService;
import com.api.cotella.service.question.dto.FitQuestionStartDTO;
import com.api.cotella.service.question.dto.TechQuestionStartDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Import({JpaTestConfiguration.class})
@TestPropertySource(locations = "/application-test-with-data.properties")
class IntervieweeAnswerServiceTest {

  @Autowired
  private InterviewUserRepository interviewUserRepository;

  @Autowired
  private IntervieweeAnswerRepository intervieweeAnswerRepository;

  @Autowired
  private InterviewQuestionService interviewQuestionService;

  @Autowired
  private IntervieweeAnswerService intervieweeAnswerService;

  InterviewUser user;

  @BeforeEach
  public void setUp() {
    user = InterviewUser.builder().name("test").email("test@test.com")
        .password("test").build();

    interviewUserRepository.save(user);
  }

  @AfterEach
  public void clean() {
    interviewUserRepository.deleteAll();
  }

  @Transactional
  @Test
  public void testSaveAnswerWhenInterviewSessionNotExist() {
    IntervieweeAnswerRequestDTO dto = new IntervieweeAnswerRequestDTO(1, 1, "test_answer");

    Exception exception = assertThrows(EntityNotFoundException.class,
        () -> intervieweeAnswerService.saveAnswerOfUser(dto));

    String expectedMessage = "Interview Session does not exist.";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Transactional
  @Test
  public void testSaveAnswerWhenInterviewQuestionNotExist() {
    TechQuestionStartDTO techQuestionStartDTO = interviewQuestionService.giveRandomTechQuestions(
        user,
        InterviewKeywordContent.DB);

    Integer NOT_VALID_QUESTION_ID = Integer.MAX_VALUE;

    IntervieweeAnswerRequestDTO dto = new IntervieweeAnswerRequestDTO(
        techQuestionStartDTO.getInterviewSessionId(), NOT_VALID_QUESTION_ID, "test_answer");

    Exception exception = assertThrows(EntityNotFoundException.class,
        () -> intervieweeAnswerService.saveAnswerOfUser(dto));

    String expectedMessage = "Interview Question does not exist.";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Transactional
  @Test
  public void testSaveTechAnswer() {
    TechQuestionStartDTO techQuestionStartDTO = interviewQuestionService.giveRandomTechQuestions(
        user,
        InterviewKeywordContent.DB);

    String answerContent = "test_answer";

    IntervieweeAnswerRequestDTO dto = new IntervieweeAnswerRequestDTO(
        techQuestionStartDTO.getInterviewSessionId(),
        techQuestionStartDTO.getTechQuestionPairDTOList().get(0).getInitialTechQuestion().getId()
        , answerContent);

    Integer savedAnswerId = intervieweeAnswerService.saveAnswerOfUser(dto);

    assertTrue(intervieweeAnswerRepository.existsById(savedAnswerId));
  }

  @Transactional
  @Test
  public void testSaveFitAnswer() {
    FitQuestionStartDTO fitQuestionStartDTO = interviewQuestionService.giveRandomFitQuestions(user,
        InterviewKeywordContent.SITUATION);
    String answerContent = "test_answer";

    IntervieweeAnswerRequestDTO dto = new IntervieweeAnswerRequestDTO(
        fitQuestionStartDTO.getInterviewSessionId(),
        fitQuestionStartDTO.getFitInterviewQuestions().get(0).getId()
        , answerContent);

    Integer savedAnswerId = intervieweeAnswerService.saveAnswerOfUser(dto);

    assertTrue(intervieweeAnswerRepository.existsById(savedAnswerId));
  }
}