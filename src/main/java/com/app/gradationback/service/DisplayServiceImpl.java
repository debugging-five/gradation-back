package com.app.gradationback.service;

import com.app.gradationback.domain.ArtImgVO;
import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.domain.ArtVO;
import com.app.gradationback.repository.DisplayDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DisplayServiceImpl implements DisplayService {

    private final DisplayDAO displayDAO;

//    작품 등록
    @Override
    public void registerArt(ArtVO artVO) {
        displayDAO.save(artVO);
    }

//    작품 이미지 등록
    @Override
    public void registerArtImg(ArtImgVO artImgVO) {
        displayDAO.save(artImgVO);
    }

//    작품 리스트
    @Override
    public List<ArtPostDTO> getArtList() {
        return displayDAO.findAll();
    }

//    작품 상세
    @Override
    public Optional<ArtPostDTO> getArt(Long id) {
        return displayDAO.findById(id);
    }
}
