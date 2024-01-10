package com.api.cotella.repository.answer;

import com.api.cotella.model.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntervieweeAnswerRepository extends JpaRepository<Answer, Integer> {

}
