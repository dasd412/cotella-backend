package com.api.cotella.service.question;

import static org.junit.jupiter.api.Assertions.*;

import com.api.cotella.config.JpaTestConfiguration;
import com.api.cotella.model.question.InterviewQuestion;
import com.api.cotella.model.question.keyword.InterviewKeywordContent;
import com.api.cotella.model.user.InterviewUser;
import com.api.cotella.repository.session.InterviewSessionRepository;
import com.api.cotella.repository.user.InterviewUserRepository;
import com.api.cotella.service.question.dto.FitQuestionStartDTO;
import com.api.cotella.service.question.dto.TechQuestionPairDTO;
import com.api.cotella.service.question.dto.TechQuestionStartDTO;
import jakarta.transaction.Transactional;
import java.util.List;
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
class InterviewQuestionServiceTest {

  @Autowired
  private InterviewUserRepository interviewUserRepository;

  @Autowired
  private InterviewQuestionService interviewQuestionService;

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
  public void testGiveTechQuestionsWhenKeywordIsFit() {
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> interviewQuestionService.giveRandomTechQuestions(user,
            InterviewKeywordContent.EXPERIENCE));

    String expectedMessage = "This method does not support fit question.";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Transactional
  @Test
  public void testGiveTechQuestions() {
    TechQuestionStartDTO dto = interviewQuestionService.giveRandomTechQuestions(user,
        InterviewKeywordContent.DB);

    List<TechQuestionPairDTO> techQuestionPairDTOList = dto.getTechQuestionPairDTOList();

    for (TechQuestionPairDTO pairDTO : techQuestionPairDTOList) {
      assertEquals(pairDTO.getInitialTechQuestion().getInterviewKeyword().getKeywordContent(),
          InterviewKeywordContent.DB);
      assertEquals(pairDTO.getFollowupTechQuestion().getAncestor(),
          pairDTO.getInitialTechQuestion().getId());
    }
  }

  @Transactional
  @Test
  public void testGiveFitQuestionsWhenKeywordIsEssential() {
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> interviewQuestionService.giveRandomFitQuestions(user,
            InterviewKeywordContent.ESSENTIAL));

    String expectedMessage = "This method supports fit keyword except essential keyword.";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }


  @Transactional
  @Test
  public void testGiveFitQuestionsWhenKeywordIsTech() {
    Exception exception = assertThrows(IllegalArgumentException.class,
        () -> interviewQuestionService.giveRandomFitQuestions(user,
            InterviewKeywordContent.JAVA));

    String expectedMessage = "This method does not support tech question.";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Transactional
  @Test
  public void testGiveFitQuestions() {
    FitQuestionStartDTO dto = interviewQuestionService.giveRandomFitQuestions(user,
        InterviewKeywordContent.SITUATION);

    assertEquals(dto.getFitInterviewQuestions().size(), 10);

    int essentialCount = 0;
    int situationCount = 0;

    for (InterviewQuestion interviewQuestion : dto.getFitInterviewQuestions()) {
      if (interviewQuestion.getInterviewKeyword().getKeywordContent()
          == InterviewKeywordContent.ESSENTIAL) {
        essentialCount += 1;
      }
      if (interviewQuestion.getInterviewKeyword().getKeywordContent()
          == InterviewKeywordContent.SITUATION) {
        situationCount += 1;
      }
    }

    assertEquals(essentialCount, 5);
    assertEquals(situationCount, 5);
  }
}