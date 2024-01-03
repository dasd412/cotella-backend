package com.api.cotella.model.llm;

import com.api.cotella.model.answer.Answer;
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
@Table(name = "fit_interview_answer_editing")
public class FitInterviewAnswerEditing {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "fit_interview_answer_editing_id")
  private Long id;

  private String content;

  @ManyToOne
  @JoinColumn(name = "answer_id")
  private Answer answer;

  public FitInterviewAnswerEditing() {
  }

  @Builder
  public FitInterviewAnswerEditing(String content, Answer answer) {
    this.content = content;
    this.answer = answer;
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
    FitInterviewAnswerEditing target = (FitInterviewAnswerEditing) obj;
    return Objects.equals(this.id, target.id);
  }
}
