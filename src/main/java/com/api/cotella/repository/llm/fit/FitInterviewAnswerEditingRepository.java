package com.api.cotella.repository.llm.fit;

import com.api.cotella.model.llm.FitInterviewAnswerEditing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FitInterviewAnswerEditingRepository extends
    JpaRepository<FitInterviewAnswerEditing, Integer> {

}
