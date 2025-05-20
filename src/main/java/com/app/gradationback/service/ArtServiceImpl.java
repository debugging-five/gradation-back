package com.app.gradationback.service;

import com.app.gradationback.domain.ArtDTO;
import com.app.gradationback.domain.ArtVO;
import com.app.gradationback.repository.ArtDAO;
import com.app.gradationback.repository.ArtImgDAO;
import com.app.gradationback.repository.ArtPostDAO;
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
    private final ArtImgDAO artImgDAO;
    private final ArtPostDAO artPostDAO;

//    작품 등록
    @Override
    public void registerArt(ArtVO artVO) {
        artDAO.save(artVO);
    }

    @Override
    public void removeArtById(Long id) {
//        게시글 삭제
        artPostDAO.deleteAllByArtId(id);
//        작품 이미지 삭제
        artImgDAO.deleteAllByArtId(id);
//        작품 삭제
        artDAO.deleteById(id);
    }

//    작품 단일 조회
    @Override
    public Optional<ArtVO> getArt(Long id) {
        return artDAO.findById(id);
    }

//    관리자용 승인 대기 목록 조회
    @Override
    public List<ArtDTO> getAllArtPending() {
        return artDAO.findAllPending();
    }

//    관리자용 승인 대기 상세 조회
    @Override
    public Optional<ArtDTO> getArtPendingById(Long id) {
        return artDAO.findPendingById(id);
    }

//    관리자용 승인 상태 변경
    @Override
    public void updateArtStatus(ArtDTO artDTO) {
        artDAO.updateStatus(artDTO);
    }
}
