package com.example.crud;

import com.example.crud.dto.CrudResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseData {

    private int code;

    private String message;

    private List<CrudResponseDto> dataList;

    private CrudResponseDto data;


}
