package com.app.gradationback.service;

import com.app.gradationback.domain.ArtImgVO;
import com.app.gradationback.repository.ArtImgDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ArtImgServiceImpl implements ArtImgService {

    private final ArtImgDAO artImgDAO;

//    작품 이미지 등록
    @Override
    public void register(ArtImgVO artImgVO) {
        artImgDAO.save(artImgVO);
    }

    @Override
    public List<ArtImgVO> getArtImgListByArtId(Long artId) {
        return artImgDAO.findAllByArtId(artId);
    }

    //    작품 이미지 전체 삭제 (작품 ID로)
    @Override
    public void removeAllByArtId(Long artId) {
        artImgDAO.deleteAllByArtId(artId);
    }
}
