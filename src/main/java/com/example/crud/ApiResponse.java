package com.example.crud;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {

    private int code;

    private String message;

    private T data;

    public static <U> ApiResponse<U> success(int code, String message, U data) {

        return new ApiResponse<U>(code, message, data);
    }

    public static <T> ApiResponse<T> failure(int code, String message) {

        return new ApiResponse<>(code, message, null);
    }

}
