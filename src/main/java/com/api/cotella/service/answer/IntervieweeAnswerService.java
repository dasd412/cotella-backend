package com.api.cotella.service.answer;

import com.api.cotella.controller.answer.request.IntervieweeAnswerRequestDTO;
import com.api.cotella.model.answer.Answer;
import com.api.cotella.model.question.InterviewQuestion;
import com.api.cotella.model.session.InterviewSession;
import com.api.cotella.repository.answer.IntervieweeAnswerRepository;
import com.api.cotella.repository.question.InterviewQuestionRepository;
import com.api.cotella.repository.session.InterviewSessionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class IntervieweeAnswerService {

  private final InterviewSessionRepository interviewSessionRepository;
  private final InterviewQuestionRepository interviewQuestionRepository;
  private final IntervieweeAnswerRepository intervieweeAnswerRepository;

  public IntervieweeAnswerService(
      InterviewSessionRepository interviewSessionRepository,
      InterviewQuestionRepository interviewQuestionRepository,
      IntervieweeAnswerRepository intervieweeAnswerRepository) {
    this.interviewSessionRepository = interviewSessionRepository;
    this.interviewQuestionRepository = interviewQuestionRepository;
    this.intervieweeAnswerRepository = intervieweeAnswerRepository;
  }

  @Transactional
  public Integer saveAnswerOfUser(
      IntervieweeAnswerRequestDTO intervieweeAnswerRequestDTO) {

    InterviewSession interviewSession = this.findSessionRecentlyAnswered(
        intervieweeAnswerRequestDTO.getInterviewSessionId());

    InterviewQuestion interviewQuestion = this.findQuestionRecentlyAnswered(
        intervieweeAnswerRequestDTO.getQuestionId());

    Answer answer = Answer.builder()
        .interviewSession(interviewSession)
        .interviewQuestion(interviewQuestion)
        .answerContent(intervieweeAnswerRequestDTO.getAnswerContent())
        .likesCount(0).dislikesCount(0)
        .build();

    return this.intervieweeAnswerRepository.save(answer).getId();
  }

  private InterviewSession findSessionRecentlyAnswered(Integer interviewSessionId) {
    return this.interviewSessionRepository.findById(interviewSessionId)
        .orElseThrow(() -> new EntityNotFoundException("Interview Session does not exist."));
  }

  private InterviewQuestion findQuestionRecentlyAnswered(Integer questionId) {
    return this.interviewQuestionRepository.findById(questionId)
        .orElseThrow(() -> new EntityNotFoundException("Interview Question does not exist."));
  }
}
