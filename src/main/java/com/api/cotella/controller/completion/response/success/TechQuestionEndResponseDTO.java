package com.api.cotella.controller.completion.response.success;

import com.api.cotella.controller.completion.response.TechQuestionEndResponse;
import com.api.cotella.repository.question.dto.ModelAnswerDTO;
import com.api.cotella.service.llm.tech.dto.RecommendedQuestionPairDTO;
import java.util.List;
import lombok.Data;

@Data
public class TechQuestionEndResponseDTO implements TechQuestionEndResponse {

  private List<ModelAnswerDTO> modelAnswerDTOList;

  private List<RecommendedQuestionPairDTO> recommendedQuestionPairDTOList;

  public TechQuestionEndResponseDTO(List<ModelAnswerDTO> modelAnswerDTOList,
      List<RecommendedQuestionPairDTO> recommendedQuestionPairDTOList) {
    this.modelAnswerDTOList = modelAnswerDTOList;
    this.recommendedQuestionPairDTOList = recommendedQuestionPairDTOList;
  }
}
