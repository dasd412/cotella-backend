package com.api.cotella.model.user;

import static com.google.common.base.Preconditions.checkArgument;

import com.api.cotella.common.util.EmailChecker;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Getter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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


  public User() {
  }

  @Builder
  public User(String name, String email, String password, String provider, String providerId) {
    checkArgument(!name.isEmpty() && name.length() <= 50, "name should be between 1 and 50");
    checkArgument(EmailChecker.checkEmail(email), "String must be format of email.");
    this.name = name;
    this.email = email;
    this.password = password;
    this.provider = provider;
    this.providerId = providerId;
  }

  public void modifyName(String name) {
    checkArgument(!name.isEmpty() && name.length() <= 50, "name should be between 1 and 50");
    this.name = name;
  }

  public void modifyEmail(String email) {
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
    User target = (User) obj;
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
