package com.api.cotella.model.answer;

import com.api.cotella.model.common.BaseTimeEntity;
import com.api.cotella.model.question.InterviewQuestion;
import com.api.cotella.model.session.InterviewSession;
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
@Table(name = "answer")
public class Answer extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "answer_id")
  private Long id;

  private String answerContent;

  private int likesCount;

  private int dislikesCount;

  @ManyToOne
  @JoinColumn(name = "interview_question_id")
  private InterviewQuestion interviewQuestion;

  @ManyToOne
  @JoinColumn(name = "interview_session_id")
  private InterviewSession interviewSession;

  public Answer() {
  }

  @Builder
  public Answer(String answerContent, int likesCount, int dislikesCount,
      InterviewQuestion interviewQuestion, InterviewSession interviewSession) {
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
}
