package com.example.demo.account.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.demo.account.domain.Account;
import com.example.demo.account.domain.Email;

// QuerydslPredicateExecutor<Account>
public interface AccountRepository extends JpaRepository<Account, Long> {
  Account findByEmail(Email email);
  boolean existsByEmail(Email email);
}

