package com.api.cotella.model.session;

import com.api.cotella.model.user.InterviewUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Getter;

@Entity
@Getter
@Table(name = "interview_session")
public class InterviewSession {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "interview_session_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "interview_user_id")
  private InterviewUser interviewUser;

  public InterviewSession() {
  }

  public InterviewSession(InterviewUser interviewUser) {
    this.interviewUser = interviewUser;
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
    InterviewSession target = (InterviewSession) obj;
    return Objects.equals(this.id, target.id);
  }
}
