package com.app.gradationback.repository;

import com.app.gradationback.domain.ArtDTO;
import com.app.gradationback.domain.ArtLikeVO;
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

//    카테고리 + 드롭다운 + 페이지네이션
    public List<ArtVO> findArtListByCategoryAndDropdown(Map<String, Object> params) {
        return artMapper.selectArtListByCategoryAndDropdown(params);
    }

    public List<ArtVO> findAllByUserId(Long userId) {
        return artMapper.selectAllByUserId(userId);
    }

//    작품 삭제
    public void deleteById(Long id) {
        artMapper.deleteById(id);
    }

//    관리자 승인 대기 목록 조회
    public List<ArtDTO> findAllPending() {
        return artMapper.selectAllPending();
    }
//    관리자 승인 대기 상세 조회
    public Optional<ArtDTO> findPendingById(Long id) {
        return artMapper.selectPendingById(id);
    }

//    관리자 승인, 반려 처리
    public void updateStatus(ArtDTO artDTO) {
        artMapper.updateStatus(artDTO);
    }

//    좋아요
    public void saveLike(ArtLikeVO artLikeVO) {
        artMapper.insertLike(artLikeVO);
    }

//    좋아요 수
    public int findLikeCount(Long artId) {
        return artMapper.selectLikeCount(artId);
    }

//    좋아요 삭제
    public void deleteLike(ArtLikeVO artLikeVO) {
        artMapper.deleteLike(artLikeVO);
    }

//    좋아요 전체 삭제
    public void deleteAllLike(Long artId) {
        artMapper.deleteAllByArtId(artId);
    }

}
