package com.api.cotella.repository.question;

import com.api.cotella.model.question.InterviewQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewQuestionRepository extends JpaRepository<InterviewQuestion, Integer>,
    InterviewQuestionRepositoryCustom {

}
