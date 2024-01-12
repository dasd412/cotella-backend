package com.api.cotella.service.answer;

import com.api.cotella.controller.answer.request.IntervieweeAnswerRequestDTO;
import com.api.cotella.repository.answer.IntervieweeAnswerRepository;
import org.springframework.stereotype.Service;

@Service
public class IntervieweeAnswerService {

  private final IntervieweeAnswerRepository intervieweeAnswerRepository;

  public IntervieweeAnswerService(IntervieweeAnswerRepository intervieweeAnswerRepository) {
    this.intervieweeAnswerRepository = intervieweeAnswerRepository;
  }

  public Integer saveAnswerOfUser(Integer userId,
      IntervieweeAnswerRequestDTO intervieweeAnswerRequestDTO) {
    return null;
  }
}
