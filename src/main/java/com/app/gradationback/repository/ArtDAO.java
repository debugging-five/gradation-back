package com.app.gradationback.repository;

import com.app.gradationback.domain.ArtVO;
import com.app.gradationback.mapper.ArtMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArtDAO {

    private final ArtMapper artMapper;

    //    작품 등록
    public void save(ArtVO artVO) {
        artMapper.insert(artVO);
    }

//    전체 작품 조회
    public List<ArtVO> findAll() {
        return artMapper.selectAll();
    }

//    단일 작품 조회
    public Optional<ArtVO> findById(Long id) {
        return artMapper.select(id);
    }

//    카테고리별 작품 조회
//    public List<ArtVO> findArtListByFilter(ArtFilterVO artFilterVo) {
//        return artMapper.selectArtListByFilter(artFilterVO);
//    }

//    카테고리 + 드롭다운 + 페이지네이션
    public List<ArtVO> findArtListByCategoryAndDropdown(Map<String, Object> params) {
        return artMapper.selectArtListByCategoryAndDropdown(params);
    }

//    작품 삭제
    public void deleteAllByArtId(Long id) {
        artMapper.deleteById(id);
    }
}
