package com.example.crud;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

    private CustomErrorCode customErrorCode;
    private String msg;

    public CustomException(CustomErrorCode customErrorCode) {
        this.customErrorCode = customErrorCode;
        this.msg = customErrorCode.getMessage();
    }
}
