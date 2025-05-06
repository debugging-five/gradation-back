package com.app.gradationback.service;


import com.app.gradationback.domain.ArtVO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ArtService {

//    작품 등록
    public void registerArt(ArtVO artVO);

//    전체 작품 조회
    public List<ArtVO> getArtList();

//    단일 작품 조회
    public Optional<ArtVO> getArt(Long id);

//    카테고리 + 드롭다운 + 페이지네이션
    public List<ArtVO> getArtListByCategoryAndDropdown(Map<String, Object> params);

//    작품 삭제
    public void removeArtById(Long id);

}
