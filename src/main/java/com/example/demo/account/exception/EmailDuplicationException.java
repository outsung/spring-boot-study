package com.example.demo.account.exception;

import com.example.demo.account.domain.Email;

import lombok.Getter;

@Getter
public class EmailDuplicationException extends RuntimeException {
  private Email email;
  private String field;

  public EmailDuplicationException(Email email) {
    this.field = "email";
    this.email = email;
  }
}
