package com.skhu.hyungil.project.mycontact.exception.handler;

import com.skhu.hyungil.project.mycontact.exception.PersonNotFoundException;
import com.skhu.hyungil.project.mycontact.exception.RenameNotPermittedException;
import com.skhu.hyungil.project.mycontact.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice //restController에 전역적으로 적용되는 핸들링이 가능
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RenameNotPermittedException.class)
    public ResponseEntity<ErrorResponse> handleRenameNoPermittedException(RenameNotPermittedException ex) {
        return new ResponseEntity<>(ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = PersonNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePersonNotFoundException(PersonNotFoundException ex) {
        return new ResponseEntity<>(ErrorResponse.of(HttpStatus.BAD_REQUEST, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        log.error("서버오류 : {}", ex.getMessage(), ex);
        return new ResponseEntity<>(ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR,"알 수 없는 서버 오류가 발생하였습니다"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
