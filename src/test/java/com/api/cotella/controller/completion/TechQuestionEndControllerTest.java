package com.api.cotella.controller.completion;

import static org.junit.jupiter.api.Assertions.*;

import com.api.cotella.repository.question.dto.ModelAnswerDTO;
import com.api.cotella.service.llm.tech.dto.RecommendedQuestionPairDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.api.cotella.service.llm.tech.QuestionRecommendationService;
import com.api.cotella.service.llm.tech.dto.TechQuestionAnswerPairDTO;
import com.api.cotella.service.llm.token.LLMTokenManageService;
import com.api.cotella.service.question.InterviewQuestionService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(TechQuestionEndController.class)
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "/application-test.properties")
public class TechQuestionEndControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private LLMTokenManageService llmTokenManageService;

  @MockBean
  private InterviewQuestionService interviewQuestionService;

  @MockBean
  private QuestionRecommendationService questionRecommendationService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void testEndTechQuestionIfFail() throws Exception {
    Integer userId = 1;
    Integer interviewSessionId = 1;
    List<TechQuestionAnswerPairDTO> list = new ArrayList<>();

    List<ModelAnswerDTO> modelAnswers = Arrays.asList(new ModelAnswerDTO(1, "test1"),
        new ModelAnswerDTO(2, "test2"));

    when(llmTokenManageService.hasEnoughTokenOfLLM()).thenReturn(false);

    when(interviewQuestionService.giveModelAnswerOfTechQuestions(any())).thenReturn(modelAnswers);
    mockMvc.perform(
            post("/users/{userId}/interview-sessions/{interviewSessionId}/tech/completion", userId,
                interviewSessionId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(list)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.modelAnswerDTOList.length()").value(modelAnswers.size()));

    verify(llmTokenManageService, times(1)).hasEnoughTokenOfLLM();
    verify(interviewQuestionService, times(1)).giveModelAnswerOfTechQuestions(any());
  }

  @Test
  public void testEndTechQuestionIfSuccess() throws Exception {
    Integer userId = 1;
    Integer interviewSessionId = 1;
    List<TechQuestionAnswerPairDTO> list = new ArrayList<>();

    List<ModelAnswerDTO> modelAnswers = Arrays.asList(new ModelAnswerDTO(1, "test1"),
        new ModelAnswerDTO(2, "test2"));
    List<RecommendedQuestionPairDTO> recommendedQuestions = Arrays.asList(
        new RecommendedQuestionPairDTO(1, "llm1"), new RecommendedQuestionPairDTO(2, "llm2"));

    when(llmTokenManageService.hasEnoughTokenOfLLM()).thenReturn(true);
    when(interviewQuestionService.giveModelAnswerOfTechQuestions(any())).thenReturn(modelAnswers);
    when(questionRecommendationService.recommendRelatedTechQuestions(any())).thenReturn(
        recommendedQuestions);

    mockMvc.perform(
            post("/users/{userId}/interview-sessions/{interviewSessionId}/tech/completion", userId,
                interviewSessionId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(list)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.modelAnswerDTOList.length()").value(modelAnswers.size()))
        .andExpect(jsonPath("$.recommendedQuestionPairDTOList.length()").value(
            recommendedQuestions.size()));

    verify(llmTokenManageService, times(1)).hasEnoughTokenOfLLM();
    verify(interviewQuestionService, times(1)).giveModelAnswerOfTechQuestions(any());
    verify(questionRecommendationService, times(1)).recommendRelatedTechQuestions(any());
  }
}