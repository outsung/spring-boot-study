package com.example.demo.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name = "user_table")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class User {
  @Id
  @GeneratedValue
  private long id;

  @Column(name = "account_id")
  private long accountId;

  @Column(name = "user_name")
  private String name;

  @Column(name = "profile_image")
  private String profileImage;
  
  @Builder
  public User(long accountId, String name, String profileImage) {
    this.accountId = accountId;
    this.name = name;
    this.profileImage = profileImage;
  }
}
