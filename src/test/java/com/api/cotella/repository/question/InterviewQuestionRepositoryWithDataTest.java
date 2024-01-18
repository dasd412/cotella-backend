package com.api.cotella.repository.question;

import static org.junit.jupiter.api.Assertions.*;

import com.api.cotella.config.JpaTestConfiguration;
import com.api.cotella.model.question.InterviewQuestion;
import com.api.cotella.model.question.keyword.InterviewKeywordContent;
import com.api.cotella.repository.question.dto.ModelAnswerDTO;
import com.api.cotella.repository.question.dto.ObjectivesDTO;
import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import({JpaTestConfiguration.class})
@TestPropertySource(locations = "/application-test-with-data.properties")
public class InterviewQuestionRepositoryWithDataTest {

  @Autowired
  InterviewQuestionRepository interviewQuestionRepository;

  @Transactional
  @Test
  public void testFindInitialRandomTechQuestions() {
    List<InterviewQuestion> initialTechQuestions = interviewQuestionRepository.findInitialRandomTechQuestions(
        InterviewKeywordContent.DB.getInterviewKeywordId());

    assertEquals(initialTechQuestions.size(), 5);
  }

  @Test
  public void testFindFollowupQuestionsForAncestors() {
    List<Integer> questionIds = Arrays.asList(1, 2, 3, 5, 6);

    List<InterviewQuestion> followupQuestions = interviewQuestionRepository.findFollowupQuestionsForAncestors(
        questionIds);

    assertEquals(followupQuestions.size(), 20);
  }

  @Test
  public void testFindRandomFitQuestionsWhenKeywordIsTech() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      interviewQuestionRepository.findRandomFitQuestions(InterviewKeywordContent.JAVA);
    });

    String expectedMessage = "This method does not support tech question.";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Transactional
  @Test
  public void testFindRandomFitQuestions() {
    List<InterviewQuestion> fitQuestions = interviewQuestionRepository.findRandomFitQuestions(
        InterviewKeywordContent.SITUATION);

    assertEquals(fitQuestions.size(), 10);

    for (int i = 0; i < 5; i++) {
      assertEquals(fitQuestions.get(i).getInterviewKeyword().getId(),
          InterviewKeywordContent.ESSENTIAL.getInterviewKeywordId());
    }

    for (int i = 5; i < 10; i++) {
      assertEquals(fitQuestions.get(i).getInterviewKeyword().getId(),
          InterviewKeywordContent.SITUATION.getInterviewKeywordId());
    }
  }

  @Transactional
  @Test
  public void testFindModelAnswersOfQuestions() {
    List<Integer> interviewQuestionIds = Arrays.asList(2, 5, 6, 7, 10);

    List<ModelAnswerDTO> modelAnswerDTOList = interviewQuestionRepository.findModelAnswersOfQuestions(
        interviewQuestionIds);

    assertEquals(modelAnswerDTOList.size(), 5);

    modelAnswerDTOList.forEach(elem -> assertNotNull(elem.getModelAnswerContent()));
  }


  @Transactional
  @Test
  public void testFindObjectivesOfQuestions() {
    List<Integer> interviewQuestionIds = Arrays.asList(177, 182, 185);

    List<ObjectivesDTO> objectivesDTOList = interviewQuestionRepository.findObjectivesOfQuestions(
        interviewQuestionIds);

    assertEquals(objectivesDTOList.size(), 3);

    objectivesDTOList.forEach(elem -> assertNotNull(elem.getObjectives()));
  }
}
