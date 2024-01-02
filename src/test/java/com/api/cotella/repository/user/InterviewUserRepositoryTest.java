package com.api.cotella.repository.user;
import com.api.cotella.config.JpaTestConfiguration;
import com.api.cotella.model.user.InterviewUser;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import({JpaTestConfiguration.class})
@TestPropertySource(locations = "/application-test.properties")
class InterviewUserRepositoryTest {

  @Mock
  private InterviewUserRepository interviewUserRepository;

  @AfterEach
  public void clean() {
    interviewUserRepository.deleteAll();
  }

  @Test
  public void testFindInterviewUserByName() {
    String name = "testUser";
    InterviewUser user = new InterviewUser();
    when(interviewUserRepository.findInterviewUserByName(name)).thenReturn(Optional.of(user));
    Optional<InterviewUser> fetchedUser = interviewUserRepository.findInterviewUserByName(name);
    assertEquals(user, fetchedUser.orElse(null));
  }

  @Test
  public void testExistName() {
    String name = "testUser";
    when(interviewUserRepository.existName(name)).thenReturn(true);
    Boolean isExist = interviewUserRepository.existName(name);
    assertTrue(isExist);
  }

  @Test
  public void testExistEmail() {
    String email = "test@test.com";
    String provider = "testProvider";
    when(interviewUserRepository.existEmail(email, provider)).thenReturn(true);
    Boolean isExist = interviewUserRepository.existEmail(email, provider);
    assertTrue(isExist);
  }
}