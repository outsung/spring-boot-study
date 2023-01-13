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
import com.example.demo.account.domain.Account;
import com.example.demo.account.dto.AccountDto;
import com.example.demo.account.dto.AccountDto.LoginRes;
import com.example.demo.account.dto.AccountDto.SignUpRes;
import com.example.demo.user.application.UserService;
import com.example.demo.user.domain.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
// @RequestMapping("auth")
@RequiredArgsConstructor
@Slf4j
public class AccountController {
  private final AccountService accountService;
  private final UserService userService;

  @PostMapping("/signup")
  @ResponseStatus(value = HttpStatus.CREATED)
  public SignUpRes signUp(@RequestBody @Valid final AccountDto.SignUpReq dto) {
    Account account =  accountService.create(dto);
    User user = userService.create(account.getId());
    log.info("userService.create : name" + user.getName());
    return new AccountDto.SignUpRes(account);
  }

  @PostMapping("/login")
  @ResponseStatus(value = HttpStatus.ACCEPTED)
  public LoginRes login(@RequestBody @Valid final AccountDto.LoginReq dto) {
    log.info("login called !!");
    return new AccountDto.LoginRes(accountService.login(dto));
  }
}
