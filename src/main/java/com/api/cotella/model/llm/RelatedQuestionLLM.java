package com.api.cotella.model.llm;

import com.api.cotella.model.question.InterviewQuestion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name = "related_question_llm")
public class RelatedQuestionLLM {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "related_question_llm_id")
  private Long id;

  private String content;

  @ManyToOne
  @JoinColumn(name = "interview_question_id")
  private InterviewQuestion interviewQuestion;

  public RelatedQuestionLLM() {
  }

  @Builder
  public RelatedQuestionLLM(String content, InterviewQuestion interviewQuestion) {
    this.content = content;
    this.interviewQuestion = interviewQuestion;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    RelatedQuestionLLM target = (RelatedQuestionLLM) obj;
    return Objects.equals(this.id, target.id);
  }
}
