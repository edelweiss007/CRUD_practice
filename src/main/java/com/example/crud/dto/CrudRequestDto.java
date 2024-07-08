package com.example.crud.dto;

import com.example.crud.entity.Composers;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CrudRequestDto {

    private Integer id;

    private String composer;

    private String writer;

    private String content;

    private String password;

    private LocalDateTime created;

    private LocalDateTime updated;

    //DTO - Entity
    public Composers toEntity(CrudRequestDto crudRequestDto) {

        return Composers.builder()
                .id(crudRequestDto.getId())
                .composer(crudRequestDto.getComposer())
                .writer(crudRequestDto.getWriter())
                .content(crudRequestDto.getContent())
                .password(crudRequestDto.getPassword())
                .created(crudRequestDto.getCreated())
                .updated(crudRequestDto.getUpdated())
                .build();
    }
}
