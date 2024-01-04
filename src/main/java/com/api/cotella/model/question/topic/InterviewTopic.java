package com.api.cotella.model.question.topic;

import com.api.cotella.model.common.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Getter;

@Getter
@Entity
@Table(name = "interview_topic")
public class InterviewTopic extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "interview_topic_id")
  private Integer id;

  @Enumerated(EnumType.STRING)
  private InterviewCategory interviewCategory;

  public InterviewTopic() {
  }

  public InterviewTopic(InterviewCategory interviewCategory) {
    this.interviewCategory = interviewCategory;
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
    InterviewTopic target = (InterviewTopic) obj;
    return Objects.equals(this.id, target.id);
  }
}
