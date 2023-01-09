package com.example.demo.account.exception;

import com.example.demo.account.domain.Email;

import lombok.Getter;

@Getter
public class AccountNotFoundException extends RuntimeException {

  private long id;
  private Email email;

  public AccountNotFoundException(long id) {
    this.id = id;
  }

  public AccountNotFoundException(Email email) {
    this.email = email;
  }
}
