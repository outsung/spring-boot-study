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

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
  @Id
  @GeneratedValue
  private long id;

  @Column(nullable = false)
  private long accountId;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String profileImage;
  
  @Builder
  public User(long accountId, String name, String profileImage) {
    this.accountId = accountId;
    this.name = name;
    this.profileImage = profileImage;
  }
}
