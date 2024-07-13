package com.example.crud;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

//@AllArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {

    private int code;

    private String message;

    private T data;

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <U> ApiResponse<U> success(int code, String message, U data) {

        return new ApiResponse<U>(code, message, data);
    }

    public static <T> ApiResponse<T> failure(CustomException customException) {

        int code = customException.getCode();
        String message = customException.getMessage();

      return new ApiResponse<>(code, message, null);
    }

}
