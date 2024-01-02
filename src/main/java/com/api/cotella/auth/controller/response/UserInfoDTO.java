package com.api.cotella.auth.controller.response;

import com.api.cotella.model.user.InterviewUser;
import lombok.Data;

@Data
public class UserInfoDTO {
  private String uid;
  private String email;

  public UserInfoDTO(InterviewUser interviewUser) {
    this.uid = interviewUser.getName();
    this.email = interviewUser.getEmail();
  }
}
