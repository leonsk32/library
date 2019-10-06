package com.example.library.restapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class LibraryExceptionHandler extends ResponseEntityExceptionHandler {
    // 自分で定義したMyExceptionをキャッチする
//    @ExceptionHandler(MyException.class)
//    public ResponseEntity<Object> handleMyException(MyException ex, WebRequest request) {
//        return super.handleExceptionInternal(ex, "handleMyException", null, HttpStatus.BAD_REQUEST, request);
//    }

    // すべての例外をキャッチする
    // どこにもキャッチされなかったらこれが呼ばれる
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        return super.handleExceptionInternal(ex, ex.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
