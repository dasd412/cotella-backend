package com.api.cotella.repository.session;

import static org.junit.jupiter.api.Assertions.*;

import com.api.cotella.config.JpaTestConfiguration;
import com.api.cotella.model.session.InterviewSession;
import com.api.cotella.model.user.InterviewUser;
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
class InterviewSessionRepositoryTest {

  @Autowired
  InterviewSessionRepository interviewSessionRepository;

  @Autowired
  InterviewUserRepository interviewUserRepository;

  InterviewUser user;

  InterviewSession session;

  @BeforeEach
  public void setUp() {
    user = InterviewUser.builder().name("test").email("test@test.com")
        .password("test").build();

    interviewUserRepository.save(user);

    session = new InterviewSession(user);
  }

  @AfterEach
  public void clean() {
    this.interviewSessionRepository.deleteAll();
    this.interviewUserRepository.deleteAll();
  }

  @Test
  public void testCreate() {
    interviewSessionRepository.save(session);
    assertNotNull(session.getId());
  }

  @Test
  public void testRead() {
    interviewSessionRepository.save(session);
    Optional<InterviewSession> foundSession = interviewSessionRepository.findById(session.getId());
    assertTrue(foundSession.isPresent());
    assertEquals(session.getId(), foundSession.get().getId());
  }

  @Test
  void testEqualsAndHashCode() {
    interviewUserRepository.save(user);

    InterviewSession session1 = new InterviewSession(user);
    InterviewSession session2 = new InterviewSession(user);

    interviewSessionRepository.save(session1);
    interviewSessionRepository.save(session2);

    // session 1과 session 2는 기본키가 다르므로 동일하지도, 동등하지도 않다. (id를 기준으로 동등함을 체크하고 있다.)
    assertNotEquals(session1, session2);
    assertNotEquals(session1.hashCode(), session2.hashCode());

    InterviewSession found = interviewSessionRepository.findById(session1.getId()).orElseThrow(
        () -> new EntityNotFoundException("Could not find entity with id: " + session1.getId()));

    // id를 기준으로 equals()와 hashcode()를 재정의했으므로 같아야 함.
    assertEquals(session1, found);
    assertEquals(session1.hashCode(), found.hashCode());
  }
}