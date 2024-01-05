package com.api.cotella.model.answer;

import static com.google.common.base.Preconditions.checkArgument;

import com.api.cotella.model.common.BaseTimeEntity;
import com.api.cotella.model.question.InterviewQuestion;
import com.api.cotella.model.session.InterviewSession;
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
@Table(name = "answer")
public class Answer extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "answer_id")
  private Integer id;

  @Lob
  @Column(name = "answer_content")
  private String answerContent;

  @Column(name = "likes_count", nullable = false, columnDefinition = "int default 0")
  private int likesCount;

  @Column(name = "dislikes_count", nullable = false, columnDefinition = "int default 0")
  private int dislikesCount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "interview_question_id")
  private InterviewQuestion interviewQuestion;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "interview_session_id")
  private InterviewSession interviewSession;

  public Answer() {
  }

  @Builder
  public Answer(String answerContent, int likesCount, int dislikesCount,
      InterviewQuestion interviewQuestion, InterviewSession interviewSession) {
    checkArgument(likesCount >= 0, "likesCount must be positive or zero.");
    checkArgument(dislikesCount >= 0, "dislikesCount must be positive or zero.");
    this.answerContent = answerContent;
    this.likesCount = likesCount;
    this.dislikesCount = dislikesCount;
    this.interviewQuestion = interviewQuestion;
    this.interviewSession = interviewSession;
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
    Answer target = (Answer) obj;
    return Objects.equals(this.id, target.id);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("id", id)
        .append("answerContent", answerContent)
        .append("likesCount", likesCount)
        .append("dislikesCount", dislikesCount)
        .toString();
  }
}
