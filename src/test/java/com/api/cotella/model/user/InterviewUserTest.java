package com.api.cotella.model.user;

import com.api.cotella.common.config.JpaConfig;
import com.api.cotella.repository.user.InterviewUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import({JpaConfig.class})
@TestPropertySource(locations = "/application-test.properties")
class InterviewUserTest {

  @Autowired
  private InterviewUserRepository userRepository;

  @AfterEach
  public void clean() {
    userRepository.deleteAll();
  }

  @Test
  void createWithNullName() {
    assertThrows(NullPointerException.class, () -> {
      InterviewUser user = InterviewUser.builder().name(null).email("test@test.com")
          .password("test").build();
      userRepository.save(user);
    });
  }

  @Test
  void createWithEmptyName() {
    assertThrows(IllegalArgumentException.class, () -> {
      InterviewUser user = InterviewUser.builder().name("").email("test@test.com").password("test")
          .build();
      userRepository.save(user);
    });
  }

  @Test
  void createWithValidName() {
    InterviewUser user = InterviewUser.builder().name("test").email("test@test.com")
        .password("test").build();
    userRepository.save(user);
    assertEquals(1, userRepository.findAll().size());
    assertEquals("test", userRepository.findAll().get(0).getName());
  }

  @Test
  void modifyNameToNull() {
    assertThrows(NullPointerException.class, () -> {
      InterviewUser user = InterviewUser.builder().name("test").email("test@test.com")
          .password("test").build();
      user.modifyName(null);
    });
  }

  @Test
  void modifyNameToEmptyString() {
    assertThrows(IllegalArgumentException.class, () -> {
      InterviewUser user = InterviewUser.builder().name("test").email("test@test.com")
          .password("test").build();
      user.modifyName("");
    });
  }

  @Test
  void modifyNameToValidString() {
    InterviewUser user = InterviewUser.builder().name("test").email("test@test.com")
        .password("test").build();
    user.modifyName("valid");
    assertEquals("valid", user.getName());
  }

  @Test
  public void createWithNullEmail() {
    assertThrows(NullPointerException.class, () -> {
      InterviewUser user = InterviewUser.builder().name("test").email(null).password("test")
          .build();
      userRepository.save(user);
    });
  }

  @Test
  public void createWithInvalidEmail() {
    assertThrows(IllegalArgumentException.class, () -> {
      InterviewUser user = InterviewUser.builder().name("test").email("test").password("test")
          .build();
      userRepository.save(user);
    });
  }

  @Test
  public void createWithValidEmail() {
    InterviewUser user = InterviewUser.builder().name("test").email("test@test.com")
        .password("test").build();
    userRepository.save(user);
    assertEquals(1, userRepository.findAll().size());
    assertEquals(user.getEmail(), userRepository.findAll().get(0).getEmail());
  }

  @Test
  public void modifyEmailToNull() {
    assertThrows(NullPointerException.class, () -> {
      InterviewUser user = InterviewUser.builder().name("test").email("test@test.com")
          .password("test").build();
      user.modifyEmail(null);
    });
  }

  @Test
  public void modifyEmailToInvalidFormat() {
    assertThrows(IllegalArgumentException.class, () -> {
      InterviewUser user = InterviewUser.builder().name("test").email("test@test.com")
          .password("test").build();
      user.modifyEmail("test");
    });
  }

  @Test
  public void modifyEmailToValidFormat() {
    InterviewUser user = InterviewUser.builder().name("test").email("test@test.com")
        .password("test").build();
    user.modifyEmail("valid@valid.net");
    assertEquals("valid@valid.net", user.getEmail());
  }
}