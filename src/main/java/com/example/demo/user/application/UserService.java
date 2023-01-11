package com.example.demo.user.application;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.account.exception.AccountNotFoundException;
import com.example.demo.user.dao.UserRepository;
import com.example.demo.user.domain.User;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public User findByAccountId(long accountId) {
    return userRepository.findByAccountId(accountId).orElseThrow(() -> new AccountNotFoundException(accountId));
  }

  @Transactional(readOnly = true)
  public User create(long accountId) {

    return userRepository.save(
      User.builder()
        .accountId(accountId)
        .name("기본이름")
        .profileImage("기본이미지")
        .build()
    );
  }
}
