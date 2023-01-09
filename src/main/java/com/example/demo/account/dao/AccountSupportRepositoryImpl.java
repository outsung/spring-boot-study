package com.example.demo.account.dao;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.account.domain.Account;

@Transactional(readOnly = true)
public class AccountSupportRepositoryImpl extends QuerydslRepositorySupport {
  public AccountSupportRepositoryImpl() {
    super(Account.class);
  }
}
