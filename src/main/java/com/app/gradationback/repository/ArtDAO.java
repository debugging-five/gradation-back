package com.app.gradationback.repository;

import com.app.gradationback.domain.ArtVO;
import com.app.gradationback.mapper.ArtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArtDAO {

    private final ArtMapper artMapper;

//    작품 정보 등록
    public void save(ArtVO artVO) {
        artMapper.insert(artVO);
    }

//    전체 작품 정보 조회
    public List<ArtVO> findAll(ArtVO artVO) {
        return artMapper.selectAll();
    }

//    단일 작품 정보 조회
    public Optional<ArtVO> findById(Long id) {
        return artMapper.select(id);
    }

//    작품 정보 수정
    public void update(ArtVO artVO) {
        artMapper.update(artVO);
    }

//    작품 정보 삭제
    public void delete(Long id) {
        artMapper.delete(id);
    }
}
