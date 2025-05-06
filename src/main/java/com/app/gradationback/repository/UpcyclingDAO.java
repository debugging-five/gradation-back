package com.app.gradationback.repository;

import com.app.gradationback.domain.UpcyclingVO;
import com.app.gradationback.mapper.UpcyclingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UpcyclingDAO {

    private final UpcyclingMapper upcyclingMapper;

    // 전체 조회
    public List<UpcyclingVO> getUpcyclingUserList() {return upcyclingMapper.selectAll();}
    // 단건 조회
    public Optional<UpcyclingVO> getByUpcyclingUser(Long id){return upcyclingMapper.select(id);}
    // 등록
    public void save(UpcyclingVO upcyclingVO) {upcyclingMapper.insert(upcyclingVO);}
    // 상태 업데이트
    public void update(UpcyclingVO upcyclingVO) {upcyclingMapper.update(upcyclingVO);}
    // 삭제
    public void delete(Long id) {upcyclingMapper.delete(id);}
}
