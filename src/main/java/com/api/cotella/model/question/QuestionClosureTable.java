package com.api.cotella.model.question;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.api.cotella.model.common.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.util.Objects;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@IdClass(QuestionClosureId.class)
@Entity
@Table(name = "question_closure_table")
public class QuestionClosureTable extends BaseTimeEntity {

  @Id
  private Integer ancestor;

  @Id
  private Integer descendant;

  @Column(nullable = false)
  private int depth;

  public QuestionClosureTable() {
  }

  public QuestionClosureTable(Integer ancestor, Integer descendant, int depth) {
    checkNotNull(ancestor);
    checkNotNull(descendant);
    checkArgument(depth >= 0, "question depth must be positive or zero.");
    this.ancestor = ancestor;
    this.descendant = descendant;
    this.depth = depth;
  }

  @Override
  public int hashCode() {
    return Objects.hash(ancestor, descendant, depth);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    QuestionClosureTable target = (QuestionClosureTable) obj;
    return Objects.equals(this.ancestor, target.ancestor) & Objects.equals(this.descendant,
        target.descendant) & Objects.equals(this.depth, target.depth);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
        .append("ancestor", ancestor)
        .append("descendant", descendant)
        .append("depth", depth)
        .toString();
  }
}
