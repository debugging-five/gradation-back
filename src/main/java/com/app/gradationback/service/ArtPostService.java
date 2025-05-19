package com.app.gradationback.service;

import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.domain.ArtPostVO;
import com.app.gradationback.domain.ArtVO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ArtPostService {

//    작품 게시글 등록
    public void register(ArtPostDTO artPostDTO);

//    작품 게시글 전체 조회
    public List<ArtPostDTO> getArtPostList();

//    작품 게시글 전체 조회 (userId로)
    public List<ArtPostDTO> getArtPostListByUserId(Long userId);

//    작품 게시글 단일 조회
    public Optional<ArtPostDTO> getArtPostById(Long id);

//    등록순으로 상위 50개 작품 조회
    public List<ArtPostDTO> getArtListForMain();

//    카테고리 + 드롭다운 + 페이지네이션
    public List<ArtPostDTO> getArtListByCategoryAndDropdown(Map<String, Object> params);

//    내 작품 리스트
    public List<ArtPostDTO> getMyArtList(Long userId);

//    내 작품 좋아요
    public List<ArtPostDTO> getLikedArtList(Long userId);

//    경매 가능 작품 조회 (좋아요 50개 이상)
    public List<ArtPostDTO> getArtListForAuction();

//    작품 게시글 수정
    public void edit(ArtPostVO artPostVO);

//    작품 게시글 삭제
    public void removeById(Long id);


}
