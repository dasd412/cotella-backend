package com.api.cotella.repository.question;

import com.api.cotella.model.question.InterviewQuestion;
import com.api.cotella.repository.user.InterviewUserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewQuestionRepository extends JpaRepository<InterviewQuestion, Integer>,
    InterviewUserRepositoryCustom {

}
