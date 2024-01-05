package com.api.cotella.repository.llm.tech;

import static org.junit.jupiter.api.Assertions.*;

import com.api.cotella.model.llm.RelatedQuestionLLM;
import com.api.cotella.model.question.InterviewQuestion;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.api.cotella.config.JpaTestConfiguration;
import com.api.cotella.repository.question.InterviewQuestionRepository;
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
class RelatedQuestionLLMRepositoryTest {

  @Autowired
  RelatedQuestionLLMRepository relatedQuestionLLMRepository;

  @Autowired
  InterviewQuestionRepository interviewQuestionRepository;

  InterviewQuestion question;

  RelatedQuestionLLM relatedQuestionLLM;

  @BeforeEach
  public void setUp() {
    question = InterviewQuestion.builder()
        .questionContent("Test question content")
        .build();

    question = interviewQuestionRepository.save(question);

    relatedQuestionLLM = RelatedQuestionLLM.builder()
        .content("Test related question content")
        .interviewQuestion(question)
        .build();
  }

  @AfterEach
  public void clean() {
    this.relatedQuestionLLMRepository.deleteAll();
    this.interviewQuestionRepository.deleteAll();
  }

  @Test
  public void testCreate() {
    relatedQuestionLLM = relatedQuestionLLMRepository.save(relatedQuestionLLM);
    assertNotNull(relatedQuestionLLM.getId());
  }

  @Test
  public void testRead() {
    relatedQuestionLLM = relatedQuestionLLMRepository.save(relatedQuestionLLM);

    Optional<RelatedQuestionLLM> found = relatedQuestionLLMRepository.findById(
        relatedQuestionLLM.getId());

    assertTrue(found.isPresent());

    assertEquals(relatedQuestionLLM.getId(), found.get().getId());
  }

  @Test
  void testEqualsAndHashCode() {
    RelatedQuestionLLM relatedQuestionLLM1 = RelatedQuestionLLM.builder()
        .content("Test related question content")
        .interviewQuestion(question)
        .build();

    RelatedQuestionLLM relatedQuestionLLM2 = RelatedQuestionLLM.builder()
        .content("Test related question content")
        .interviewQuestion(question)
        .build();

    relatedQuestionLLMRepository.save(relatedQuestionLLM1);
    relatedQuestionLLMRepository.save(relatedQuestionLLM2);

    assertNotEquals(relatedQuestionLLM1, relatedQuestionLLM2);
    assertNotEquals(relatedQuestionLLM1.hashCode(), relatedQuestionLLM2.hashCode());

    RelatedQuestionLLM found = relatedQuestionLLMRepository.findById(relatedQuestionLLM1.getId())
        .orElseThrow(() -> new EntityNotFoundException(
            "Could not find entity with id: " + relatedQuestionLLM1.getId()));

    assertEquals(relatedQuestionLLM1, found);
    assertEquals(relatedQuestionLLM1.hashCode(), found.hashCode());
  }
}