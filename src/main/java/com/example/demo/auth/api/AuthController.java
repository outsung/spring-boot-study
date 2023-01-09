package com.example.demo.auth.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.auth.dto.AuthDto;
import com.example.demo.auth.dto.AuthDto.LoginRes;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("accounts")
@RequiredArgsConstructor
public class AuthController {
 
  @PostMapping
  @ResponseStatus(value = HttpStatus.OK)
  public LoginRes login(@RequestBody @Valid final AuthDto.SignUpReq dto) {
    // dto
    return new LoginRes("accessToken", "refreshToken");
  }
}

