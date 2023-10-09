package com.example.demo.exception;


public class NotMatchPasswordException extends RuntimeException {
    @Override
    public String getMessage() {
        return "비밀번호가 일치하지 않습니다.";
    }
}
