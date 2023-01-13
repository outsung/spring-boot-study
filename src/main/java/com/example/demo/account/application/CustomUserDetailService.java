package com.example.demo.account.application;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.demo.account.dao.AccountRepository;
import com.example.demo.account.exception.AccountNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomUserDetailService implements UserDetailsService {
  private final AccountRepository accountRepository;

  public UserDetails loadUserByUsername(String accountIdString) {
    long id = Long.valueOf(accountIdString);
    return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
  }
  
}
