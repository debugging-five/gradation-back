package com.app.gradationback.service;

import com.app.gradationback.domain.ArtImgVO;
import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.domain.ArtPostVO;
import com.app.gradationback.domain.ArtVO;
import com.app.gradationback.repository.ArtDAO;
import com.app.gradationback.repository.ArtImgDAO;
import com.app.gradationback.repository.ArtPostDAO;
import com.app.gradationback.repository.CommentDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class DisplayServiceImpl implements DisplayService {

    private final ArtPostDAO artPostDAO;
    private final ArtDAO artDAO;
    private final ArtImgDAO artImgDAO;
    private final ArtVO artVO;
    private final ArtImgVO artImgVO;
    private final ArtPostVO artPostVO;
    private final CommentDAO commentDAO;

    //    게시글 등록
    @Override
    public void register(ArtPostDTO artPostDTO) {

//        작품
        ArtVO artVO = new ArtVO();
        artVO.setArtTitle(artPostDTO.getArtTitle());
        artVO.setArtCategory(artPostDTO.getArtCategory());
        artVO.setArtMaterial(artPostDTO.getArtMaterial());
        artVO.setArtSize(artPostDTO.getArtSize());
        artVO.setArtDescription(artPostDTO.getArtDescription());
        artVO.setArtEndDate(artPostDTO.getArtEndDate());
        artVO.setUserId(artPostDTO.getUserId());

//        작품 이미지
        ArtImgVO artImgVO = new ArtImgVO();
        artImgVO.setArtImgName(artPostDTO.getArtImgName());
        artImgVO.setArtImgPath(artPostDTO.getArtImgPath());
        artImgVO.setArtId(artPostDTO.getArtId());

//        게시글
        ArtPostVO artPostVO = new ArtPostVO();
        artPostVO.setArtId(artPostDTO.getArtId());
        artPostVO.setUserId(artPostDTO.getUserId());
        artPostVO.setArtPostDate(artPostDTO.getArtPostDate());

        artDAO.save(artVO);
        artImgDAO.save(artImgVO);
        artPostDAO.save(artPostVO);
    }

//    게시글 전체 조회
    @Override
    public List<ArtPostDTO> getArtPostList() {
        return artPostDAO.findAll();
    }

//    게시글 단일 조회
    @Override
    public Optional<ArtPostDTO> getArtPost(Long id) {
        return artPostDAO.find(id);
    }

//    게시글 수정
    @Override
    public void editArtPost(ArtPostDTO artPostDTO) {
//        ArtPostVO artPostVO = new ArtPostVO();
//        artPostVO.setArtPostDate(artPostDTO.getArtPostDate());

//        작품
        ArtVO artVO = new ArtVO();
        artVO.setId(artPostDTO.getId());
        artVO.setArtTitle(artPostDTO.getArtTitle());
        artVO.setArtCategory(artPostDTO.getArtCategory());
        artVO.setArtMaterial(artPostDTO.getArtMaterial());
        artVO.setArtSize(artPostDTO.getArtSize());
        artVO.setArtDescription(artPostDTO.getArtDescription());
        artVO.setArtEndDate(artPostDTO.getArtEndDate());

//        작품 이미지
        ArtImgVO artImgVO = new ArtImgVO();
        artImgVO.setId(artPostDTO.getArtId());
        artImgVO.setArtImgName(artPostDTO.getArtImgName());
        artImgVO.setArtImgPath(artPostDTO.getArtImgPath());
        artImgVO.setArtId(artPostDTO.getArtId());

//        게시글
        ArtPostVO artPostVO = new ArtPostVO();
        artPostVO.setId(artPostDTO.getArtId());
        artPostVO.setArtId(artPostDTO.getArtId());
        artPostVO.setUserId(artPostDTO.getUserId());
        artPostVO.setArtPostDate(artPostDTO.getArtPostDate());

        artDAO.update(artVO);
        artImgDAO.update(artImgVO);
        artPostDAO.update(artPostVO);
    }

//    게시글 삭제 + 댓글 삭제
    @Override
    public void removeArtPost(Long id) {
        commentDAO.deleteAllByPostId(id);
        artPostDAO.delete(id);
    }
}
