package com.api.cotella.model.question;

import com.api.cotella.model.common.BaseTimeEntity;
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

@Getter
@Entity
@Table(name = "question_meta_data")
public class QuestionMetaData extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "question_meta_data_id")
  private Integer id;

  private String modelAnswerContent;

  private String objectives;

  @ManyToOne
  @JoinColumn(name = "interview_question_id")
  private InterviewQuestion interviewQuestion;

  public QuestionMetaData() {
  }

  @Builder
  public QuestionMetaData(String modelAnswerContent, String objectives,
      InterviewQuestion interviewQuestion) {
    this.modelAnswerContent = modelAnswerContent;
    this.objectives = objectives;
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
    QuestionMetaData target = (QuestionMetaData) obj;
    return Objects.equals(this.id, target.id);
  }
}
