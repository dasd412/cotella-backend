package com.api.cotella.auth.controller;

import com.api.cotella.auth.InterviewUserDetailsService;
import com.api.cotella.auth.controller.response.UserInfoDTO;
import com.api.cotella.common.util.RequestUtil;
import com.api.cotella.model.user.InterviewUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
public class InterviewUserController {

  private final FirebaseAuth firebaseAuth;

  private final InterviewUserDetailsService interviewUserDetailsService;

  public InterviewUserController(FirebaseAuth firebaseAuth,
      InterviewUserDetailsService interviewUserDetailsService) {
    this.firebaseAuth = firebaseAuth;
    this.interviewUserDetailsService = interviewUserDetailsService;
  }

  @PostMapping("")
  public UserInfoDTO register(@RequestHeader("Authorization") String authorization) {
    FirebaseToken decodedToken;
    try {
      String token = RequestUtil.getAuthorizationToken(authorization);
      decodedToken = firebaseAuth.verifyIdToken(token);
    } catch (IllegalArgumentException | FirebaseAuthException e) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
          "{\"code\":\"INVALID_TOKEN\", \"message\":\"" + e.getMessage() + "\"}");
    }

    InterviewUser registeredUser = interviewUserDetailsService.register(
        decodedToken.getUid(), decodedToken.getEmail());

    return new UserInfoDTO(registeredUser);
  }

  @GetMapping("/me")
  public UserInfoDTO getUserMe(Authentication authentication) {
    InterviewUser customUser = ((InterviewUser) authentication.getPrincipal());
    return new UserInfoDTO(customUser);
  }
}
