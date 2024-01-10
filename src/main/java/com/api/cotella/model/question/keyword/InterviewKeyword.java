package com.api.cotella.model.question.keyword;

import static com.google.common.base.Preconditions.checkNotNull;

import com.api.cotella.model.common.BaseTimeEntity;
import com.api.cotella.model.question.topic.InterviewTopic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@Entity
@Table(name = "interview_keyword")
public class InterviewKeyword extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "interview_keyword_id")
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private InterviewKeywordContent keywordContent;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "interview_topic_id")
  private InterviewTopic interviewTopic;

  public InterviewKeyword() {
  }

  @Builder
  public InterviewKeyword(InterviewKeywordContent interviewKeywordContent,
      InterviewTopic interviewTopic) {
    checkNotNull(interviewKeywordContent);
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

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("id", id)
        .append("keywordContent", keywordContent)
        .toString();
  }
}
