package com.example.crud.dto;

import com.example.crud.entity.Composers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CrudResponseDto {

    private Integer id;

    private String composer;

    private String writer;

    private String content;

    private String password;

    private LocalDateTime created;

    private LocalDateTime updated;


    //Entity -> DTO
    public static CrudResponseDto toDto(Composers crud) {

        return CrudResponseDto.builder()
                .id(crud.getId())
                .composer(crud.getComposer())
                .writer(crud.getWriter())
                .content(crud.getContent())
                .password(crud.getPassword())
                .created(crud.getCreated())
                .updated(crud.getUpdated())
                .build();
    }
}
