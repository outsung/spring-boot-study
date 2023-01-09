package com.example.demo.error;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.demo.account.exception.AccountNotFoundException;
import com.example.demo.account.exception.EmailDuplicationException;


import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ErrorExceptionController {
  @ExceptionHandler(value = {
    AccountNotFoundException.class
  })

  @ResponseStatus(HttpStatus.NOT_FOUND)
  protected ErrorResponse handleAccountNotFoundException(AccountNotFoundException e) {
    final ErrorCode accountNotFound = ErrorCode.ACCOUNT_NOT_FOUND;
    log.error(accountNotFound.getMessage(), e.getId());
    return buildError(accountNotFound);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    final List<ErrorResponse.FieldError> fieldErrors = getFieldErrors(e.getBindingResult());
    return buildFieldErrors(ErrorCode.INPUT_VALUE_INVALID, fieldErrors);
  }

  @ExceptionHandler(BindException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ErrorResponse handleBindException(BindException e) {
    final List<ErrorResponse.FieldError> fieldErrors = getFieldErrors(e.getBindingResult());
    return buildFieldErrors(ErrorCode.INPUT_VALUE_INVALID, fieldErrors);
  }


  @ExceptionHandler(EmailDuplicationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ErrorResponse handleConstraintViolationException(EmailDuplicationException e) {
    final ErrorCode errorCode = ErrorCode.EMAIL_DUPLICATION;
    log.error(errorCode.getMessage(), e.getEmail() + e.getField());
    return buildError(errorCode);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  protected ErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException e) {
    log.error(e.getMessage());
    return buildError(ErrorCode.INPUT_VALUE_INVALID);
  }


  private List<ErrorResponse.FieldError> getFieldErrors(BindingResult bindingResult) {
  final List<FieldError> errors = bindingResult.getFieldErrors();
  return errors.parallelStream()
          .map(error -> ErrorResponse.FieldError.builder()
                  .reason(error.getDefaultMessage())
                  .field(error.getField())
                  .value((String) error.getRejectedValue())
                  .build())
          .collect(Collectors.toList());
  }


  private ErrorResponse buildError(ErrorCode errorCode) {
  return ErrorResponse.builder()
          .code(errorCode.getCode())
          .status(errorCode.getStatus())
          .message(errorCode.getMessage())
          .build();
  }

  private ErrorResponse buildFieldErrors(ErrorCode errorCode, List<ErrorResponse.FieldError> errors) {
  return ErrorResponse.builder()
          .code(errorCode.getCode())
          .status(errorCode.getStatus())
          .message(errorCode.getMessage())
          .errors(errors)
          .build();
  }  
}
