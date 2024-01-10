package com.api.cotella.repository.question;

import static org.junit.jupiter.api.Assertions.*;

import com.api.cotella.config.JpaTestConfiguration;
import com.api.cotella.model.question.InterviewQuestion;
import com.api.cotella.model.question.keyword.InterviewKeyword;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
@TestPropertySource(locations = "/application-test.properties")
class InterviewQuestionRepositoryTest {

  @Autowired
  InterviewQuestionRepository interviewQuestionRepository;

  InterviewQuestion question;

  @BeforeEach
  public void setUp() {
    question = InterviewQuestion.builder()
        .questionContent("Test question content")
        .build();
  }

  @AfterEach
  public void clean() {
    this.interviewQuestionRepository.deleteAll();
  }

  @Test
  public void testCreate() {
    question = interviewQuestionRepository.save(question);
    assertNotNull(question.getId());
  }

  @Test
  public void testRead() {
    question = interviewQuestionRepository.save(question);

    Optional<InterviewQuestion> found = interviewQuestionRepository.findById(
        question.getId());

    assertTrue(found.isPresent());

    assertEquals(question.getId(), found.get().getId());
    assertEquals(question.getQuestionContent(), found.get().getQuestionContent());
  }

  @Test
  public void updateQuestionContent() {
    question = interviewQuestionRepository.save(question);

    String updatedContent = "TTT";

    question.modifyQuestionContent(updatedContent);

    interviewQuestionRepository.save(question);

    Optional<InterviewQuestion> found = interviewQuestionRepository.findById(
        question.getId());

    assertTrue(found.isPresent());

    assertEquals(updatedContent, found.get().getQuestionContent());
  }

  @Test
  void testEqualsAndHashCode() {
    InterviewQuestion question1 = InterviewQuestion.builder()
        .questionContent("Test question content")
        .build();

    InterviewQuestion question2 = InterviewQuestion.builder()
        .questionContent("Test question content")
        .build();

    interviewQuestionRepository.save(question1);
    interviewQuestionRepository.save(question2);

    assertNotEquals(question1, question2);
    assertNotEquals(question1.hashCode(), question2.hashCode());

    InterviewQuestion found = interviewQuestionRepository.findById(question1.getId())
        .orElseThrow(() -> new EntityNotFoundException(
            "Could not find entity with id: " + question1.getId()));

    assertEquals(question1, found);
    assertEquals(question1.hashCode(), found.hashCode());
  }
}