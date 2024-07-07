package com.example.crud.Controller;

import com.example.crud.Service.CrudService;
import com.example.crud.dto.CrudRequestDto;
import com.example.crud.dto.CrudResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<CrudResponseDto> getComposers() {

        List<CrudResponseDto> composersList = crudService.getComposers();

        return composersList;
    }

    //작곡가 등록
    @PostMapping("/composers")
    public CrudResponseDto createComposer(@RequestBody CrudRequestDto requestDto) {

        CrudResponseDto CreatedComposer = crudService.createComposer(requestDto);

        return CreatedComposer;
    }

    //작곡가 조회
    @GetMapping("/composers/{composer}")
    public List<CrudResponseDto> getComposer(@PathVariable String composer) {

        List<CrudResponseDto> selectedComposer = crudService.getComposer(composer);

        return selectedComposer;
    }

    //작곡가 수정
    @PutMapping("/composers/{composer}")
    public CrudResponseDto updateComposer(@PathVariable String composer, @RequestBody CrudRequestDto requestDto) throws Exception {

        CrudResponseDto updatedComposer = crudService.updateComposer(composer, requestDto);

        return updatedComposer;
    }

    //작곡가 삭제
    @DeleteMapping("/composers/{composer}")
    public String deleteComposer(@PathVariable String composer, @RequestBody CrudRequestDto requestDto) throws Exception{

        crudService.deleteComposer(composer, requestDto);

        return "deleted successfully";
    }
}
