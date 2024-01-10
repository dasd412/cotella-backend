package com.api.cotella.model.llm;

import static com.google.common.base.Preconditions.checkNotNull;

import com.api.cotella.model.common.BaseTimeEntity;
import com.api.cotella.model.question.InterviewQuestion;
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
@Table(name = "related_question_llm")
public class RelatedQuestionLLM extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "related_question_llm_id")
  private Integer id;

  @Lob
  @Column(nullable = false)
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "interview_question_id")
  private InterviewQuestion interviewQuestion;

  public RelatedQuestionLLM() {
  }

  @Builder
  public RelatedQuestionLLM(String content, InterviewQuestion interviewQuestion) {
    checkNotNull(content);
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

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("id", id)
        .append("content", content)
        .toString();
  }
}
