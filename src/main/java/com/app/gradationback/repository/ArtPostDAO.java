package com.app.gradationback.repository;

import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.domain.ArtPostVO;
import com.app.gradationback.domain.ArtVO;
import com.app.gradationback.mapper.ArtMapper;
import com.app.gradationback.mapper.ArtPostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArtPostDAO {

    private final ArtPostMapper artPostMapper;

//    작품 게시글 등록
    public void save(ArtPostVO artPostVO) {
        artPostMapper.insert(artPostVO);
    }

//    작품 게시글 전체 조회
    public List<ArtPostDTO> findAll() {
        return artPostMapper.selectAll();
    }

//    작품 게시글 단일 조회
    public Optional<ArtPostDTO> findById(Long id) {
        return artPostMapper.select(id);
    }

//    카테고리 + 드롭다운 + 페이지네이션
    public List<ArtPostDTO> findArtListByCategoryAndDropdown(Map<String, Object> params) {
        return artPostMapper.selectArtListByCategoryAndDropdown(params);
    }

    public List<ArtPostDTO> findAllByUserId(Long userId) { return artPostMapper.selectAllByUserId(userId); }

    public List<ArtPostDTO> findAllMyArt(Long userId) { return artPostMapper.selectAllMyArt(userId); }

    public List<ArtPostDTO> findAllLikedArt(Long userId) { return artPostMapper.selectAllLikedArt(userId); }

    public List<ArtPostDTO> findAllForAuction() {
        return artPostMapper.selectAllForAuction();
    }

//    작품 게시글 수정
    public void update(ArtPostVO artPostVO) {
        artPostMapper.update(artPostVO);
    }

//    작품 게시글 삭제
    public void deleteById(Long id) {
        artPostMapper.deleteById(id);
    }

//    작품 게시글 전체 삭제 (회원 탈퇴)
    public void deleteAllByUserId(Long userId) {
        artPostMapper.deleteAllByUserId(userId);
    }

    public void deleteAllByArtId(Long artId) { artPostMapper.deleteAllByArtId(artId); }

}
