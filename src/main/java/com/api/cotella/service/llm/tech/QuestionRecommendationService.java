package com.api.cotella.service.llm.tech;

import com.api.cotella.controller.completion.request.TechQuestionEndRequestDTO;
import com.api.cotella.repository.llm.tech.RelatedQuestionLLMRepository;
import com.api.cotella.service.llm.tech.dto.RelatedTechQuestionDTO;
import org.springframework.stereotype.Service;

@Service
public class QuestionRecommendationService {

  private final RelatedQuestionLLMRepository relatedQuestionLLMRepository;

  public QuestionRecommendationService(RelatedQuestionLLMRepository relatedQuestionLLMRepository) {
    this.relatedQuestionLLMRepository = relatedQuestionLLMRepository;
  }

  public RelatedTechQuestionDTO recommendRelatedTechQuestions(
      TechQuestionEndRequestDTO techQuestionEndRequestDTO) {
    return null;
  }
}
