package com.app.gradationback.mapper;

import com.app.gradationback.domain.ArtDTO;
import com.app.gradationback.domain.ArtLikeVO;
import com.app.gradationback.domain.ArtVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface ArtMapper {

//    작품 등록
    public void insert(ArtVO artVO);

//    카테고리 + 드롭다운 + 페이지네이션
    public List<ArtVO> selectArtListByCategoryAndDropdown(Map<String, Object> params);

    public List<ArtVO> selectAllByUserId(Long userId);

//    작품 삭제
    public void deleteById(Long id);

//    관리자용 승인 대기 목록 조회
    public List<ArtDTO> selectAllPending();

//    관리자용 승인 대기 상세 조회
    public Optional<ArtDTO> selectPendingById(Long id);

//    관리자용 승인 상태 변경
    public void updateStatus(ArtDTO artDTO);

//    좋아요
    public void insertLike(ArtLikeVO artLikeVO);

//    좋아요 수
    public int selectLikeCount(Long artId);

//    좋아요 취소
    public void deleteLike(ArtLikeVO artLikeVO);

//    좋아요 삭제
    public void deleteAllByArtId(Long artId);
}
