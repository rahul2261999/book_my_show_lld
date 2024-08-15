package org.example.bookmyshow.controllers;

import org.example.bookmyshow.dtos.ResponseStatus;
import org.example.bookmyshow.dtos.SignUpRequestDto;
import org.example.bookmyshow.dtos.SignUpResponseDto;
import org.example.bookmyshow.models.User;
import org.example.bookmyshow.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
  private UserService userService;

  UserController(
      UserService userService
  ) {
    this.userService = userService;
  }

  public SignUpResponseDto signUp(SignUpRequestDto body) {
    SignUpResponseDto response = new SignUpResponseDto();

    try {
      User user = userService.signUp(body.getEmail(), body.getPassword());

      response.setUserId(user.getId());
    } catch (Exception ex) {
      response.setResponseStatus(ResponseStatus.FAILURE);
    }

    return response;
  }
}
