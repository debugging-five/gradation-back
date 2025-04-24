package com.app.gradationback.repository;

import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.domain.ArtPostVO;
import com.app.gradationback.domain.ArtVO;
import com.app.gradationback.mapper.ArtMapper;
import com.app.gradationback.mapper.ArtPostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArtPostDAO {

    private final ArtPostMapper artPostMapper;

//    작품 게시
    public void save(ArtPostVO artPostVO) {
        artPostMapper.insert(artPostVO);
    }

//    작품 게시 조회 (전체)
    public List<ArtPostDTO> findAll() {
        return artPostMapper.selectAll();
    }

//    작품 게시 조회 (단일)
    public Optional<ArtPostDTO> find(Long id) {
        return artPostMapper.select(id);
    }

//    작품 게시 수정
    public void update(ArtPostVO artPostVO) {
        artPostMapper.update(artPostVO);
    }

//    작품 게시 삭제
    public void delete(Long id) {
        artPostMapper.delete(id);
    }
}
