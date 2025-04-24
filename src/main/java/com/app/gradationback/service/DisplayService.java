package com.app.gradationback.service;

import com.app.gradationback.domain.ArtImgVO;
import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.domain.ArtVO;

import java.util.List;
import java.util.Optional;

public interface DisplayService {

//    작품 등록
    public void registerArt(ArtVO artVO);

//    작품 이미지 등록
    public void registerArtImg(ArtImgVO artImgVO);

//    작품 리스트
    public List<ArtPostDTO> getArtList();

//    작품 상세
    public Optional<ArtPostDTO> getArt(Long id);
}
