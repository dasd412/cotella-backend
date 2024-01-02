package com.api.cotella.auth;

import com.api.cotella.model.user.InterviewUser;
import com.api.cotella.repository.user.InterviewUserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InterviewUserDetailsService implements UserDetailsService {

  private final InterviewUserRepository interviewUserRepository;

  public InterviewUserDetailsService(InterviewUserRepository interviewUserRepository) {
    this.interviewUserRepository = interviewUserRepository;
  }

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    InterviewUser user=interviewUserRepository.findInterviewUserByName(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));
    return new PrincipalDetails(user);
  }
}
