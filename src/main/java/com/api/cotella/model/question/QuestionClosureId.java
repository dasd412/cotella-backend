package com.api.cotella.model.question;

import java.io.Serializable;
import java.util.Objects;

public class QuestionClosureId implements Serializable {

  private Integer ancestor;
  private Integer descendant;

  @Override
  public int hashCode() {
    return Objects.hash(ancestor, descendant);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    QuestionClosureId target = (QuestionClosureId) obj;
    return Objects.equals(this.ancestor, target.ancestor) & Objects.equals(this.descendant,
        target.descendant);
  }
}
