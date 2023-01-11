package com.example.demo.account.application;


import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.account.dao.AccountRepository;
import com.example.demo.account.domain.Account;
import com.example.demo.account.domain.Email;
import com.example.demo.account.dto.AccountDto;
import com.example.demo.account.exception.AccountNotFoundException;
import com.example.demo.account.exception.EmailDuplicationException;
import com.example.demo.account.exception.LoginFailedException;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.user.application.UserService;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

  private final JwtTokenProvider jwtTokenProvider;
  private final AccountRepository accountRepository;
  private final UserService userService;
  

  @Transactional(readOnly = true)
  public Account findById(long id) {
    return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
  }

  @Transactional(readOnly = true)
  public Account findByEmail(final Email email) {
      final Account account = accountRepository.findByEmail(email);
      if (account == null) throw new AccountNotFoundException(email);
      return account;
  }

  @Transactional(readOnly = true)
  public boolean isExistedEmail(Email email) {
      return accountRepository.existsByEmail(email);
  }

  public Account create(AccountDto.SignUpReq dto) {
    if (isExistedEmail(dto.getEmail()))
        throw new EmailDuplicationException(dto.getEmail());
      
    Account account = accountRepository.save(dto.toEntity());
    userService.create(account.getId());
    return account;
  }

  public String login(AccountDto.LoginReq dto) {
    final Account account = findByEmail(dto.getEmail());
    
    if(!account.getPassword().isMatched(dto.getPassword())){
      throw new LoginFailedException();
    }

    List<String> roles = new ArrayList<>();
    roles.add("USER");

    return jwtTokenProvider.createToken(String.valueOf(account.getId()), roles);
  }

}
