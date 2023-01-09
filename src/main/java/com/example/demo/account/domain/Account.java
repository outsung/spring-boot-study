package com.example.demo.account.domain;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.common.model.DateTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "account")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {
  @Id
  @GeneratedValue
  private long id;
  
  @Embedded
  private Email email;

  @Embedded
  private Password password;

  @Embedded
  private DateTime dateTime;

  @Builder
  public Account(Email email, Password password) {
    this.email = email;
    this.password = password;
  }
}
