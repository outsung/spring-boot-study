package com.example.demo.auth.dto;

import javax.validation.Valid;

import com.example.demo.account.domain.Account;
import com.example.demo.account.domain.Email;
import com.example.demo.account.domain.Password;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AuthDto {
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

    public Account toEntity() {
      return Account.builder()
                    .email(this.email)
                    .password(Password.builder().value(this.password).build())
                    .build();
    }

  }

  
  @Getter
  public static class LoginRes {
    private String accessToken;
    private String refreshToken;

    public LoginRes(String accessToken, String refreshToken) {
      this.accessToken = accessToken;
      this.refreshToken = refreshToken;
    }
  }

}
