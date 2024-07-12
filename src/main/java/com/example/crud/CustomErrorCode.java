package com.example.crud;

import lombok.Getter;
import org.springframework.http.HttpStatus;

//@AllArgsConstructor 모든 필드값을 파라미터로 받는 생성자를 자동 생성
@Getter
public enum CustomErrorCode {

    WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),
    COMPOSER_DOESNT_EXIST(HttpStatus.NOT_FOUND, "찾을 수 없는 작곡가입니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메서드 입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "지정한 리소스를 찾을 수 없습니다.");

    private HttpStatus httpStatus;
    private String message;

    //자꾸 @AllArgsConstructor을 쓰니까 생성자가 눈에 안보여서 헛갈린다!! 눈으로 봐야지..
    CustomErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
