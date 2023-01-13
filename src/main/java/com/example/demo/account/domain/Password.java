package com.example.demo.account.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {
  @Column(name = "password", nullable = false)
  private String value;

  @Builder
  public Password(final String value) {
    this.value = encodePassword(value);
  }
  
  public boolean isPasswordMatched(final String rawPassword) {
    return isMatches(rawPassword);
  }

  public void changePassword(final String newPassword, final String oldPassword) {
    if (isPasswordMatched(oldPassword)) {
      this.value = encodePassword(newPassword);
    }
  }

  private String encodePassword(final String password) {
    return new BCryptPasswordEncoder().encode(password);
  }
  private boolean isMatches(String rawPassword) {
    return new BCryptPasswordEncoder().matches(rawPassword, this.value);
  }
}
