package com.api.cotella.repository.answer;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.api.cotella.config.JpaTestConfiguration;
import com.api.cotella.model.answer.Answer;
import com.api.cotella.model.question.InterviewQuestion;
import com.api.cotella.model.session.InterviewSession;
import com.api.cotella.model.user.InterviewUser;
import com.api.cotella.repository.question.InterviewQuestionRepository;
import com.api.cotella.repository.session.InterviewSessionRepository;
import com.api.cotella.repository.user.InterviewUserRepository;
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
class IntervieweeAnswerRepositoryTest {

  @Autowired
  InterviewUserRepository interviewUserRepository;

  @Autowired
  IntervieweeAnswerRepository intervieweeAnswerRepository;

  @Autowired
  InterviewQuestionRepository interviewQuestionRepository;

  @Autowired
  InterviewSessionRepository interviewSessionRepository;

  Answer answer;

  InterviewQuestion question;

  InterviewSession session;

  @BeforeEach
  public void setUp() {
    InterviewUser user = InterviewUser.builder().name("test").email("test@test.com")
        .password("test").build();

    session = new InterviewSession(user);

    question = InterviewQuestion.builder().questionContent("test").build();

    interviewUserRepository.save(user);

    interviewSessionRepository.save(session);

    interviewQuestionRepository.save(question);

    answer = Answer.builder().answerContent("test").likesCount(0).dislikesCount(0)
        .interviewSession(session).interviewQuestion(question).build();
  }

  @AfterEach
  public void clean() {
    this.intervieweeAnswerRepository.deleteAll();
    this.interviewQuestionRepository.deleteAll();
    this.interviewSessionRepository.deleteAll();
    this.interviewUserRepository.deleteAll();
  }

  @Test
  public void testCreate() {
    answer = intervieweeAnswerRepository.save(answer);
    assertNotNull(answer.getId());
  }

  @Test
  public void testRead() {
    answer = intervieweeAnswerRepository.save(answer);

    Optional<Answer> found = intervieweeAnswerRepository.findById(answer.getId());

    assertTrue(found.isPresent());

    assertEquals(found.get().getId(), answer.getId());
  }

  @Test
  public void increaseLikesCount() {
    answer = intervieweeAnswerRepository.save(answer);

    Answer found = intervieweeAnswerRepository.findById(
        answer.getId()).orElseThrow(EntityNotFoundException::new);

    found.increaseLikesCount();

    intervieweeAnswerRepository.save(found);

    Answer updated = intervieweeAnswerRepository.findById(
        answer.getId()).orElseThrow(EntityNotFoundException::new);

    assertEquals(updated.getLikesCount(), 1);
  }

  @Test
  public void increaseDislikesCount() {
    answer = intervieweeAnswerRepository.save(answer);

    Answer found = intervieweeAnswerRepository.findById(
        answer.getId()).orElseThrow(EntityNotFoundException::new);

    found.increaseDislikesCount();

    intervieweeAnswerRepository.save(found);

    Answer updated = intervieweeAnswerRepository.findById(
        answer.getId()).orElseThrow(EntityNotFoundException::new);

    assertEquals(updated.getDislikesCount(), 1);
  }


  @Test
  public void testEqualsAndHashCode() {
    Answer answer1 = Answer.builder().answerContent("test").likesCount(0).dislikesCount(0)
        .interviewSession(session).interviewQuestion(question).build();

    Answer answer2 = Answer.builder().answerContent("test").likesCount(0).dislikesCount(0)
        .interviewSession(session).interviewQuestion(question).build();

    intervieweeAnswerRepository.save(answer1);
    intervieweeAnswerRepository.save(answer2);

    assertNotEquals(answer1, answer2);
    assertNotEquals(answer1.hashCode(), answer2.hashCode());

    Answer found = intervieweeAnswerRepository.findById(
        answer1.getId()).orElseThrow(EntityNotFoundException::new);

    assertEquals(answer1, found);
    assertEquals(answer1.hashCode(), found.hashCode());
  }
}