package com.example.crud.Controller;

import com.example.crud.ApiResponse;
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
    public ResponseEntity<ApiResponse> getComposers() {

            List<CrudResponseDto> composersList = crudService.getComposers();

            ApiResponse<List<CrudResponseDto>> response = ApiResponse.success(200, "요청에 성공하였습니다.", composersList);

            return new ResponseEntity<>(response, HttpStatus.OK);

    }

    //작곡가 등록
    @PostMapping("/composers")
    public ResponseEntity<ApiResponse> createComposer(@RequestBody CrudRequestDto requestDto) {

            CrudResponseDto createdComposer = crudService.createComposer(requestDto);

            ApiResponse<CrudResponseDto> response = ApiResponse.success(200, "작곡가 등록에 성공하였습니다.", createdComposer);

            return new ResponseEntity<>(response, HttpStatus.OK);

    }

    //작곡가 조회
    @GetMapping("/composers/{composer}")
    public ResponseEntity<ApiResponse> getComposer(@PathVariable String composer) throws Exception {

            List<CrudResponseDto> selectedComposer = crudService.getComposer(composer);

            ApiResponse<List<CrudResponseDto>> response = ApiResponse.success(200, "작곡가 조회에 성공하였습니다.", selectedComposer);

            return new ResponseEntity<>(response, HttpStatus.OK);

    }

    //작곡가 수정
    @PutMapping("/composers/{composer}")
    public ResponseEntity<ApiResponse> updateComposer(@PathVariable String composer, @RequestBody CrudRequestDto requestDto) throws Exception {

            CrudResponseDto updatedComposer = crudService.updateComposer(composer, requestDto);

            ApiResponse<CrudResponseDto> response = ApiResponse.success(200, "작곡가 수정에 성공하였습니다.", updatedComposer);

            return new ResponseEntity<>(response, HttpStatus.OK);

    }

    //작곡가 삭제
    @DeleteMapping("/composers/{composer}")
    public ResponseEntity<ApiResponse> deleteComposer(@PathVariable String composer, @RequestBody CrudRequestDto requestDto) throws Exception {

            crudService.deleteComposer(composer, requestDto);

            ApiResponse<Object> response = ApiResponse.success(200, "작곡가 삭제에 성공하였습니다.", null);

            return new ResponseEntity<>(response, HttpStatus.OK);

    }
}

