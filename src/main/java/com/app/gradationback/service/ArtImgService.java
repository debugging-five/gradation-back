package com.app.gradationback.service;

import com.app.gradationback.domain.ArtImgVO;

import java.util.List;
import java.util.Optional;

public interface ArtImgService {

//    작품 이미지 등록
    public void register(ArtImgVO artImgVO);

    public List<ArtImgVO> getArtImgListByArtId(Long artId);

//    작품 이미지 전체 삭제 (작품 ID로)
    public void removeAllByArtId(Long artId);
}
