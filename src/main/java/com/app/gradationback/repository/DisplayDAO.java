package com.app.gradationback.repository;

import com.app.gradationback.domain.ArtImgVO;
import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.domain.ArtVO;
import com.app.gradationback.mapper.DisplayMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DisplayDAO {

    private final DisplayMapper displayMapper;

//    전시 등록
    public void save(ArtVO artVO) {
        displayMapper.insertArt(artVO);
    }

//    전시 이미지 등록
    public void save(ArtImgVO artImgVO) {
        displayMapper.insertArtImg(artImgVO);
    }

//    전시 리스트
    public List<ArtPostDTO> findAll() {
        return displayMapper.selectAll();
    }

//    전시 상세
    public Optional<ArtPostDTO> findById(Long id) {
        return displayMapper.select(id);
    }
}
