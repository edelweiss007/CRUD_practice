package com.example.crud.Service;

import com.example.crud.Repository.CrudRepository;
import com.example.crud.dto.CrudRequestDto;
import com.example.crud.dto.CrudResponseDto;
import com.example.crud.entity.Composers;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrudService {

    private final CrudRepository crudRepository;

    @Autowired
    public CrudService(CrudRepository crudRepository) {

        this.crudRepository = crudRepository;
    }

    //전체 작곡가 리스트
    public List<CrudResponseDto> getComposers() {

        List<Composers> all = crudRepository.findAll();

        //List 객체를 스트림으로 변환 -> map으로 CrudResponseDto에서 만든 클래스 타입의 toDto 메서드 사용
        //-> collect의 Collectors.toList()로 다시 리스트로 변환
        List<CrudResponseDto> responseDto = all.stream().map(CrudResponseDto::toDto).collect(Collectors.toUnmodifiableList());

        return responseDto;
    }

    //작곡가 등록
    @Transactional
    public CrudResponseDto createComposer(CrudRequestDto crudRequestDto) {

        Composers saved = crudRepository.save(crudRequestDto.toEntity(crudRequestDto));

        Composers composers = new Composers();

        CrudResponseDto dto = composers.toDto(saved);

        return dto;
    }

    //작곡가 조회
    public List<CrudResponseDto> getComposer(String composer) throws Exception {

        List<Composers> foundByComposer = crudRepository.findByComposer(composer);

        List<CrudResponseDto> responseDto = foundByComposer.stream().map(CrudResponseDto::toDto).collect(Collectors.toUnmodifiableList());

        if(responseDto.isEmpty()) {

            throw new Exception();

        } else {

            return responseDto;
        }

    }

    //작곡가 수정
    @Transactional
    public CrudResponseDto updateComposer(String composer, CrudRequestDto requestDto) throws Exception {
        //작곡가 이름에 따른 정보를 찾아온다.
        List<Composers> foundByComposer = crudRepository.findByComposer(composer);

        //찾아온 Entity를 Dto로 변환한다.
        List<CrudResponseDto> responseDto = foundByComposer.stream().map(CrudResponseDto::toDto).collect(Collectors.toUnmodifiableList());
        //저장된 비밀번호를 꺼낸다.
        String savedPassword = responseDto.get(0).getPassword();

        //입력한 비밀번호와 저장된 비밀번호가 같지 않으면 예외를 발생시킨다.
        if(!(requestDto.getPassword().equals(savedPassword))) {
            throw new Exception();
        }

        Composers composers = crudRepository.findByWriter(responseDto.get(0).getWriter());

        composers.update(requestDto);

        CrudResponseDto dto = CrudResponseDto.toDto(composers);

        return dto;
    }

    //작곡가 삭제
    public void deleteComposer(String composer, CrudRequestDto requestDto) throws Exception {
        //작곡가 이름으로 정보를 찾아온다.
        List<Composers> foundByComposer = crudRepository.findByComposer(composer);
        //Entity -> Dto로 변환
        List<CrudResponseDto> responseDto = foundByComposer.stream().map(CrudResponseDto::toDto).collect(Collectors.toUnmodifiableList());
        //DB에 저장된 비밀번호와 삭제할 때 보낸 비밀번호가 일치하지 않으면 예외를 발생시킨다.
        if (!(requestDto.getPassword().equals(responseDto.get(0).getPassword()))) {
            throw new Exception();
        }

        crudRepository.deleteById(responseDto.get(0).getId());
    }
}
