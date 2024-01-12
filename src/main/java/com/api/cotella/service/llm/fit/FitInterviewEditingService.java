package com.api.cotella.service.llm.fit;

import com.api.cotella.repository.llm.fit.FitInterviewAnswerEditingRepository;
import com.api.cotella.service.llm.fit.dto.FitInterviewEditingByLLMDTO;
import com.api.cotella.service.llm.fit.dto.FitInterviewEditingResultDTO;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FitInterviewEditingService {

  private final FitInterviewAnswerEditingRepository fitInterviewAnswerEditingRepository;

  public FitInterviewEditingService(
      FitInterviewAnswerEditingRepository fitInterviewAnswerEditingRepository) {
    this.fitInterviewAnswerEditingRepository = fitInterviewAnswerEditingRepository;
  }

  public List<FitInterviewEditingResultDTO> editFitInterviewAnswersOfUser(
      List<FitInterviewEditingByLLMDTO> fitInterviewEditingByLLMDTOList) {
    return null;
  }
}
