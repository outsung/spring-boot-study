package com.example.demo.user.dto;

import com.example.demo.user.domain.User;

import lombok.Getter;

public class UserDto {
  @Getter
  public static class Res {
    private String name;
    private String profileImage;

    public Res(User user) {
      this.name = user.getName();
      this.profileImage = user.getProfileImage();
    }
  }
  
}
