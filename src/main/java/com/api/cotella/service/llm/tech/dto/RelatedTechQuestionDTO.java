package com.api.cotella.service.llm.tech.dto;

import java.util.List;
import lombok.Data;

@Data
public class RelatedTechQuestionDTO {

  private List<RecommendedQuestionPairDTO> recommendedQuestionPairDTOList;

  public RelatedTechQuestionDTO(List<RecommendedQuestionPairDTO> recommendedQuestionPairDTOList) {
    this.recommendedQuestionPairDTOList = recommendedQuestionPairDTOList;
  }
}
