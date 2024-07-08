package com.example.crud.Controller;

import com.example.crud.Service.CrudService;
import com.example.crud.dto.CrudRequestDto;
import com.example.crud.dto.CrudResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //@Controller + @ResponseBody
public class CrudController {

    private final CrudService crudService;

    @Autowired
    public CrudController(CrudService crudService) {

        this.crudService = crudService;
    }

    //전체 작곡가 리스트 조회
    @GetMapping("/composers")
    public ResponseEntity getComposers() {

        try {
            List<CrudResponseDto> composersList = crudService.getComposers();

            return ResponseEntity.status(HttpStatus.OK).body(composersList);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 요청입니다.");
        }

    }

    //작곡가 등록
    @PostMapping("/composers")
    public ResponseEntity createComposer(@RequestBody CrudRequestDto requestDto) {

        try {
            CrudResponseDto CreatedComposer = crudService.createComposer(requestDto);

            return ResponseEntity.status(HttpStatus.OK).body(CreatedComposer);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 요청입니다.");
        }

    }

    //작곡가 조회
    @GetMapping("/composers/{composer}")
    public ResponseEntity getComposer(@PathVariable String composer) {

        try {
            List<CrudResponseDto> selectedComposer = crudService.getComposer(composer);

            return ResponseEntity.status(HttpStatus.OK).body(selectedComposer);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("잘못된 요청입니다.");
        }

    }

    //작곡가 수정
    @PutMapping("/composers/{composer}")
    public ResponseEntity updateComposer(@PathVariable String composer, @RequestBody CrudRequestDto requestDto) {

        try {
            CrudResponseDto updatedComposer = crudService.updateComposer(composer, requestDto);

            return ResponseEntity.status(HttpStatus.OK).body(updatedComposer);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("일치하지 않는 비밀번호입니다.");
        }

    }

    //작곡가 삭제
    @DeleteMapping("/composers/{composer}")
    public ResponseEntity deleteComposer(@PathVariable String composer, @RequestBody CrudRequestDto requestDto) {

        try {
            crudService.deleteComposer(composer, requestDto);

            return ResponseEntity.status(HttpStatus.OK).body("deleted successfully");

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("비밀번호가 일치하지 않습니다.");
        }

    }
}
