package com.example.crud;

import com.example.crud.dto.CrudResponseDto;
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

    public static <T>ApiResponse<T> success(int code, String message, T data) {

        return new ApiResponse<>(code, message, data);
    }

    public static <T>ApiResponse<T> failure(int code, String message) {

        return new ApiResponse<>(code, message, null);
    }

}
