package com.example.crud.entity;

import com.example.crud.dto.CrudRequestDto;
import com.example.crud.dto.CrudResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor//파라미터 없는 생성자를 생성
@AllArgsConstructor //모든 필드값을 파라미터로 받는 생성자를 생성
@Builder
@Entity
@Table(name = "composers")
public class Composers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String composer;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(nullable = false)
    private LocalDateTime updated;

    public CrudResponseDto toDto(Composers composers) {

        return CrudResponseDto.builder()
                .id(composers.getId())
                .composer(composers.getComposer())
                .writer(composers.getWriter())
                .content(composers.getContent())
                .password(composers.getPassword())
                .created(composers.getCreated())
                .updated(composers.getUpdated())
                .build();
    }

    public void update(CrudRequestDto requestDto) {
        this.composer = requestDto.getComposer();
        this.content = requestDto.getContent();
        this.writer = requestDto.getWriter();
    }

}
