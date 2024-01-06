package com.api.cotella.repository.llm.fit;

import static org.junit.jupiter.api.Assertions.*;

import com.api.cotella.config.JpaTestConfiguration;
import com.api.cotella.model.answer.Answer;
import com.api.cotella.model.llm.FitInterviewAnswerEditing;
import com.api.cotella.repository.answer.IntervieweeAnswerRepository;
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
class FitInterviewAnswerEditingRepositoryTest {

  @Autowired
  FitInterviewAnswerEditingRepository fitInterviewAnswerEditingRepository;

  @Autowired
  IntervieweeAnswerRepository intervieweeAnswerRepository;

  FitInterviewAnswerEditing editing;

  Answer answer;

  @BeforeEach
  public void setUp() {
    answer = Answer.builder().answerContent("test").likesCount(0).dislikesCount(0).build();

    answer = intervieweeAnswerRepository.save(answer);

    editing = FitInterviewAnswerEditing.builder().content("test").answer(answer).build();
  }

  @AfterEach
  public void clean() {
    this.fitInterviewAnswerEditingRepository.deleteAll();
    this.intervieweeAnswerRepository.deleteAll();
  }

  @Test
  public void testCreate() {
    editing = fitInterviewAnswerEditingRepository.save(editing);
    assertNotNull(editing.getId());
  }

  @Test
  public void testRead() {
    editing = fitInterviewAnswerEditingRepository.save(editing);

    Optional<FitInterviewAnswerEditing> found = fitInterviewAnswerEditingRepository.findById(
        editing.getId());

    assertTrue(found.isPresent());

    assertEquals(editing.getId(), found.get().getId());
    assertEquals(editing.getContent(), found.get().getContent());
    assertEquals(editing.getAnswer(), found.get().getAnswer());
  }

  @Test
  public void testEqualsAndHashCode() {
    FitInterviewAnswerEditing editing1 = FitInterviewAnswerEditing.builder().content("test")
        .answer(answer).build();

    FitInterviewAnswerEditing editing2 = FitInterviewAnswerEditing.builder().content("test")
        .answer(answer).build();

    fitInterviewAnswerEditingRepository.save(editing1);
    fitInterviewAnswerEditingRepository.save(editing2);

    assertNotEquals(editing1, editing2);
    assertNotEquals(editing1.hashCode(), editing2.hashCode());

    FitInterviewAnswerEditing found = fitInterviewAnswerEditingRepository.findById(
        editing1.getId()).orElseThrow(EntityNotFoundException::new);

    assertEquals(editing1, found);
    assertEquals(editing1.hashCode(), found.hashCode());
  }
}