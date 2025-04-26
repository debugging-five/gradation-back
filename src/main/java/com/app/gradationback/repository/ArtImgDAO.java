package com.app.gradationback.repository;

import com.app.gradationback.domain.ArtImgVO;
import com.app.gradationback.mapper.ArtImgMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArtImgDAO {

    private final ArtImgMapper artImgMapper;

//    작품 이미지 등록
    public void save(ArtImgVO artImgVO) {
        artImgMapper.insert(artImgVO);
    }

//    전체 작품 이미지 조회
    public List<ArtImgVO> findAll() {
        return artImgMapper.selectAll();
    }

//    단일 작품 이미지 조회
    public Optional<ArtImgVO> findById(Long id) {
        return artImgMapper.select(id);
    }

//    작품 이미지 수정
    public void update(ArtImgVO artImgVO) {
        artImgMapper.update(artImgVO);
    }

//    작품 이미지 삭제
    public void delete(Long id) {
        artImgMapper.delete(id);
    }

}
