package com.api.cotella.repository.llm.tech;

import com.api.cotella.model.llm.RelatedQuestionLLM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatedQuestionLLMRepository extends JpaRepository<RelatedQuestionLLM, Integer> {

}
