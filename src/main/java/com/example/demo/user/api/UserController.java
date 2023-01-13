package com.example.demo.user.api;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.user.application.UserService;
import com.example.demo.user.domain.User;
import com.example.demo.user.dto.UserDto.Res;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
  private final UserService userService;

  @GetMapping("/me")
  @ResponseStatus(value = HttpStatus.OK)
  public Res me() {
    log.info("users/me called !");
    
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    long id = Long.parseLong(authentication.getName());

    User user = userService.findByAccountId(id);
    return new Res(user);
  }
  
}
