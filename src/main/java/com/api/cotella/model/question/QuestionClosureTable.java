package com.api.cotella.model.question;

import com.api.cotella.model.common.BaseTimeEntity;

public class QuestionClosureTable extends BaseTimeEntity {

  private Long ancestor;
  private Long descendant;
  private int depth;
}
