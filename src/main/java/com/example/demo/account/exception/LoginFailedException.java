package com.example.demo.account.exception;

import lombok.Getter;

@Getter
public class LoginFailedException extends RuntimeException {
  
  public LoginFailedException() {}
}

