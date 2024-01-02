package com.api.cotella.repository.user;

import com.api.cotella.model.user.InterviewUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewUserRepository extends JpaRepository<InterviewUser,Long> {

}
