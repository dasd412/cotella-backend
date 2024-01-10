package com.api.cotella.model.question;

import com.api.cotella.model.common.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@Entity
@Table(name = "question_metadata")
public class QuestionMetaData extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "question_meta_data_id")
  private Integer id;

  @Lob
  @Column(name="model_answer_content")
  private String modelAnswerContent;

  @Lob
  private String objectives;

  @ManyToOne(fetch = FetchType.LAZY)
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

  public void modifyModelAnswerContent() {
    this.modelAnswerContent = modelAnswerContent;
  }

  public void modifyObjectives() {
    this.objectives = objectives;
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

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("id", id)
        .append("modelAnswerContent", modelAnswerContent)
        .append("objectives", objectives)
        .toString();
  }
}
