package com.example.crud.Controller;

import com.example.crud.ResponseData;
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
    public ResponseEntity<ResponseData> getComposers() {

        ResponseData responseData = new ResponseData();

        try {
            List<CrudResponseDto> composersList = crudService.getComposers();

            responseData.setCode(200);
            responseData.setMessage("요청에 성공하였습니다.");
            responseData.setDataList(composersList);

            return new ResponseEntity<>(responseData, HttpStatus.OK);

        } catch (Exception e) {

            responseData.setCode(400);
            responseData.setMessage("잘못된 요청입니다.");
            responseData.setDataList(null);

            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }

    }

    //작곡가 등록
    @PostMapping("/composers")
    public ResponseEntity<ResponseData> createComposer(@RequestBody CrudRequestDto requestDto) {

        ResponseData responseData = new ResponseData();

        try {
            CrudResponseDto createdComposer = crudService.createComposer(requestDto);

            responseData.setCode(200);
            responseData.setMessage("작곡가 등록에 성공하였습니다.");
            responseData.setData(createdComposer);

            return new ResponseEntity<>(responseData, HttpStatus.OK);

        } catch (Exception e) {

            responseData.setCode(400);
            responseData.setMessage("잘못된 요청입니다.");
            responseData.setData(null);

            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }

    }

    //작곡가 조회
    @GetMapping("/composers/{composer}")
    public ResponseEntity<ResponseData> getComposer(@PathVariable String composer) {

        ResponseData responseData = new ResponseData();

        try {
            List<CrudResponseDto> selectedComposer = crudService.getComposer(composer);

            responseData.setCode(200);
            responseData.setMessage("작곡가 조회에 성공하였습니다.");
            responseData.setDataList(selectedComposer);

            return new ResponseEntity<>(responseData, HttpStatus.OK);

        } catch (Exception e) {

            responseData.setCode(400);
            responseData.setMessage("잘못된 요청입니다.");
            responseData.setDataList(null);

            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }

    }

    //작곡가 수정
    @PutMapping("/composers/{composer}")
    public ResponseEntity<ResponseData> updateComposer(@PathVariable String composer, @RequestBody CrudRequestDto requestDto) throws Exception {

        ResponseData responseData = new ResponseData();

        try {
            CrudResponseDto updatedComposer = crudService.updateComposer(composer, requestDto);

            responseData.setCode(200);
            responseData.setMessage("작곡가 수정에 성공하였습니다.");
            responseData.setData(updatedComposer);

            return new ResponseEntity<>(responseData, HttpStatus.OK);

        } catch (Exception e) {

            responseData.setCode(400);
            responseData.setMessage("알치하지 않는 비밀번호 입니다.");
            responseData.setData(null);

            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }

    }

    //작곡가 삭제
    @DeleteMapping("/composers/{composer}")
    public ResponseEntity<ResponseData> deleteComposer(@PathVariable String composer, @RequestBody CrudRequestDto requestDto) {

        ResponseData responseData = new ResponseData();

        try {
            crudService.deleteComposer(composer, requestDto);

            responseData.setCode(200);
            responseData.setMessage("작곡가 삭제에 성공하였습니다.");

            return new ResponseEntity<>(responseData, HttpStatus.OK);

        } catch (Exception e) {

            responseData.setCode(400);
            responseData.setMessage("일치하지 않는 비밀번호입니다.");

            return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
        }

    }
}

