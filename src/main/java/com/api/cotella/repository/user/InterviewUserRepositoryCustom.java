package com.api.cotella.repository.user;

import com.api.cotella.model.user.InterviewUser;
import java.util.Optional;

public interface InterviewUserRepositoryCustom {

  Optional<InterviewUser> findInterviewUserByName(String username);
  Boolean existName(String username);
  Boolean existEmail(String email, String provider);
}
