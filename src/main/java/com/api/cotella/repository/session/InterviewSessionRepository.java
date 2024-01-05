package com.api.cotella.repository.session;

import com.api.cotella.model.session.InterviewSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewSessionRepository extends JpaRepository<InterviewSession, Integer> {

}
