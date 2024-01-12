package com.api.cotella.service.llm.tech;

import com.api.cotella.repository.llm.tech.RelatedQuestionLLMRepository;
import com.api.cotella.service.llm.tech.dto.RecommendedQuestionPairDTO;
import com.api.cotella.service.llm.tech.dto.TechQuestionAnswerPairDTO;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class QuestionRecommendationService {

  private final RelatedQuestionLLMRepository relatedQuestionLLMRepository;

  public QuestionRecommendationService(RelatedQuestionLLMRepository relatedQuestionLLMRepository) {
    this.relatedQuestionLLMRepository = relatedQuestionLLMRepository;
  }

  public List<RecommendedQuestionPairDTO> recommendRelatedTechQuestions(
      List<TechQuestionAnswerPairDTO> techQuestionAnswerPairDTOList) {
    return null;
  }
}
