package com.api.cotella.service.llm.fit;

import com.api.cotella.repository.llm.fit.FitInterviewAnswerEditingRepository;
import com.api.cotella.service.llm.LLMCallService;
import com.api.cotella.service.llm.fit.dto.FitInterviewEditingByLLMDTO;
import com.api.cotella.service.llm.fit.dto.FitInterviewEditingResultDTO;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"local", "dev", "prod"})
@Service
public class FitInterviewEditingService {

  private final FitInterviewAnswerEditingRepository fitInterviewAnswerEditingRepository;

  private final LLMCallService llmCallService;

  public FitInterviewEditingService(
      FitInterviewAnswerEditingRepository fitInterviewAnswerEditingRepository,
      LLMCallService llmCallService) {
    this.fitInterviewAnswerEditingRepository = fitInterviewAnswerEditingRepository;
    this.llmCallService = llmCallService;
  }

  public List<FitInterviewEditingResultDTO> editFitInterviewAnswersOfUser(
      List<FitInterviewEditingByLLMDTO> fitInterviewEditingByLLMDTOList) {
    return null;
  }
}
