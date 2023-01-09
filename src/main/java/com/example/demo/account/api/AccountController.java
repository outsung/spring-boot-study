package com.example.demo.account.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.account.application.AccountService;
import com.example.demo.account.dao.AccountRepository;
import com.example.demo.account.dto.AccountDto;
import com.example.demo.account.dto.AccountDto.Res;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("accounts")
@RequiredArgsConstructor
public class AccountController {
  private final AccountService accountService;
  private final AccountRepository accountRepository;

  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public Res signUp(@RequestBody @Valid final AccountDto.SignUpReq dto) {
    return new AccountDto.Res(accountService.create(dto));
  }

}
