package com.example.demo.account.dto;

import javax.validation.Valid;

import com.example.demo.account.domain.Account;
import com.example.demo.account.domain.Email;
import com.example.demo.account.domain.Password;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AccountDto {
  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public static class SignUpReq {

    @Valid
    private Email email;
    private String password;

    @Builder
    public SignUpReq(Email email, String password) {
      this.email = email;
      this.password = password;
    }

    public Account toEntity() {
      return Account.builder()
                    .email(this.email)
                    .password(Password.builder().value(this.password).build())
                    .build();
    }

  }

  @Getter
  public static class Res {
    private Email email;
    private Password password;

    public Res(Account account) {
        this.email = account.getEmail();
        this.password = account.getPassword();
    }
  }
}
