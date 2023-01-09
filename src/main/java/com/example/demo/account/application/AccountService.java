package com.example.demo.account.application;


import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.account.dao.AccountRepository;
import com.example.demo.account.domain.Account;
import com.example.demo.account.domain.Email;
import com.example.demo.account.dto.AccountDto;
import com.example.demo.account.exception.AccountNotFoundException;
import com.example.demo.account.exception.EmailDuplicationException;

import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

  private final AccountRepository accountRepository;

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
    return accountRepository.save(dto.toEntity());
  }

}
