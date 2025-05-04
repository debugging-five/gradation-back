package com.app.gradationback.service;

import com.app.gradationback.domain.ArtVO;
import com.app.gradationback.repository.ArtDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ArtServiceImpl implements ArtService {

    private final ArtDAO artDAO;

//    작품 등록
    @Override
    public void registerArt(ArtVO artVO) {
        artDAO.save(artVO);
    }

//    전체 작품 조회
    @Override
    public List<ArtVO> getArtList() {
        return artDAO.findAll();
    }

    @Override
    public Optional<ArtVO> getArt(Long id) {
        return artDAO.findById(id);
    }

    @Override
    public List<ArtVO> getArtListByCategoryAndDropdown(Map<String, Object> params) {
        return artDAO.findArtListByCategoryAndDropdown(params);
    }

    @Override
    public void removeArtById(Long id) {
        artDAO.deleteAllByArtId(id);
    }
}
