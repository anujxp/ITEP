package com.info.user.exception;

public class UnautherizedException extends RuntimeException{
    public UnautherizedException(String msg) {
        super(msg);
    }

}