package com.api.cotella.model.question;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "interview_keyword")
public class InterviewKeyword {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "interview_keyword_id")
  private Long id;

  @Enumerated(EnumType.STRING)
  private InterviewKeywordContent keywordContent;

  @ManyToOne
  @JoinColumn(name = "interview_topic_id")
  private InterviewTopic interviewTopic;

  public InterviewKeyword() {
  }

  @Builder
  public InterviewKeyword(InterviewKeywordContent interviewKeywordContent,
      InterviewTopic interviewTopic) {
    this.keywordContent = interviewKeywordContent;
    this.interviewTopic = interviewTopic;
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
    InterviewKeyword target = (InterviewKeyword) obj;
    return Objects.equals(this.id, target.id);
  }
}
