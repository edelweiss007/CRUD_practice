package com.example.crud;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

    private int code;
    private String message;

    public CustomException(CustomErrorCode customErrorCode) {

        this.code = customErrorCode.getHttpStatus().value();
        this.message = customErrorCode.getMsg();
    }
}
