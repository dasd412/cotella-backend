package com.api.cotella.model.user;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.api.cotella.common.util.EmailChecker;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Getter
@Table(name="interview_user")
public class InterviewUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="interview_user_id")
  private Long id;

  @Column(nullable = false, unique = true)
  private String name;

  @Column
  private String password;

  @Column
  private String email;

  @Column
  private String provider;

  @Column
  private String providerId;


  public InterviewUser() {
  }

  @Builder
  public InterviewUser(String name, String email, String password, String provider, String providerId) {
    checkNotNull(name,"name must not be null");
    checkNotNull(email,"email must not be null");
    checkArgument(!name.isEmpty() && name.length() <= 50, "name should be between 1 and 50");
    checkArgument(EmailChecker.checkEmail(email), "String must be format of email.");
    this.name = name;
    this.email = email;
    this.password = password;
    this.provider = provider;
    this.providerId = providerId;
  }

  public void modifyName(String name) {
    checkNotNull(name,"name must not be null");
    checkArgument(!name.isEmpty() && name.length() <= 50, "name should be between 1 and 50");
    this.name = name;
  }

  public void modifyEmail(String email) {
    checkNotNull(email,"email must not be null");
    checkArgument(EmailChecker.checkEmail(email), "String must be format of email.");
    this.email = email;
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
    InterviewUser target = (InterviewUser) obj;
    return Objects.equals(this.id, target.id);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("id", id)
        .append("name", name)
        .append("email", email)
        .append("provider", provider)
        .toString();
  }
}
