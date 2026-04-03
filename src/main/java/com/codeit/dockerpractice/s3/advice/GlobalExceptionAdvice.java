package com.codeit.dockerpractice.s3.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {
  public ResponseEntity handleRuntimeException(RuntimeException e){
    log.error("Runtime Exception: {}", e.getMessage(), e);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        . body(e.getMessage() != null? e.getMessage() : "잘못된 요청");
  }
}
