package com.example.demo.account.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public static class LoginReq {

    @Valid
    private Email email;
    private String password;

    @Builder
    public LoginReq(Email email, String password) {
      this.email = email;
      this.password = password;
    }
  }

  @Getter
  public static class SignUpRes {
    private Email email;
    private Password password;

    public SignUpRes(Account account) {
        this.email = account.getEmail();
        this.password = account.getPasswordTPassword();
    }
  }

  @Getter
  public static class LoginRes {
    private String token;

    public LoginRes(String token) {
      this.token = token;
    }
  }
}
