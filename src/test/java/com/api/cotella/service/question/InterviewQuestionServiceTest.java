package com.api.cotella.service.question;

import static org.junit.jupiter.api.Assertions.*;

import com.api.cotella.config.JpaTestConfiguration;
import com.api.cotella.model.question.keyword.InterviewKeywordContent;
import com.api.cotella.model.user.InterviewUser;
import com.api.cotella.repository.user.InterviewUserRepository;
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

    assertEquals(dto.getInterviewSessionId(), 1);

    List<TechQuestionPairDTO> techQuestionPairDTOList = dto.getTechQuestionPairDTOList();

    for (TechQuestionPairDTO pairDTO : techQuestionPairDTOList) {
      assertEquals(pairDTO.getInitialTechQuestion().getInterviewKeyword().getKeywordContent(),
          InterviewKeywordContent.DB);
      assertEquals(pairDTO.getFollowupTechQuestion().getAncestor(),
          pairDTO.getInitialTechQuestion().getId());
    }
  }
}