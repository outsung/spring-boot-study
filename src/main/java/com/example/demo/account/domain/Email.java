package com.example.demo.account.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties({"host", "id"})
public class Email {
  @javax.validation.constraints.Email
  @Column(name = "email", nullable = false, unique = true)
  private String value;

  @Builder
  public Email(String value) {
    this.value = value;
  }

  public static Email of(String email) {
    return new Email(email);
  }

  public String getHost() {
    int index = value.indexOf("@");
    return value.substring(index);
  }

  public String getId() {
    int index = value.indexOf("@");
    return value.substring(0, index);
  }
}
