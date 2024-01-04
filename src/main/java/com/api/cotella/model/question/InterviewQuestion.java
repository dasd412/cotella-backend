package com.api.cotella.model.question;

import com.api.cotella.model.common.BaseTimeEntity;
import com.api.cotella.model.question.keyword.InterviewKeyword;
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
@Table(name = "interview_question")
public class InterviewQuestion extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "interview_question_id")
  private Integer id;

  private String questionContent;

  @ManyToOne
  @JoinColumn(name = "interview_keyword_id")
  private InterviewKeyword interviewKeyword;

  public InterviewQuestion() {
  }

  @Builder
  public InterviewQuestion(String questionContent, InterviewKeyword interviewKeyword) {
    this.questionContent = questionContent;
    this.interviewKeyword = interviewKeyword;
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
    InterviewQuestion target = (InterviewQuestion) obj;
    return Objects.equals(this.id, target.id);
  }
}
