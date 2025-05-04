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
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ArtPostServiceImpl implements ArtPostService {

    private final ArtPostDAO artPostDAO;
    private final ArtImgDAO artImgDAO;
    private final ArtDAO artDAO;
    private final CommentDAO commentDAO;

//    작품 게시글 등록 (작품 정보 + 작품 이미지 + 작품 게시글)
    @Override
    public void register(ArtPostDTO artPostDTO) {
        ArtVO artVO = new ArtVO();
        artVO.setArtTitle(artPostDTO.getArtTitle());
        artVO.setArtCategory(artPostDTO.getArtCategory());
        artVO.setArtMaterial(artPostDTO.getArtMaterial());
        artVO.setArtSize(artPostDTO.getArtSize());
        artVO.setArtDescription(artPostDTO.getArtDescription());
        artVO.setArtEndDate(artPostDTO.getArtEndDate());
        artVO.setUserId(artPostDTO.getUserId());
        artDAO.save(artVO);

        Long artId = artVO.getId();

        ArtImgVO artImgVO = new ArtImgVO();
//        artImgVO.setArtId(artPostDTO.getArtId());
        artImgVO.setArtId(artId);
        artImgVO.setArtImgName(artPostDTO.getArtImgName());
        artImgVO.setArtImgPath(artPostDTO.getArtImgPath());
        artImgDAO.save(artImgVO);

        ArtPostVO artPostVO = new ArtPostVO();
//        artPostVO.setArtId(artPostDTO.getArtId());
        artPostVO.setArtId(artId);
        artPostVO.setArtPostDate(artPostDTO.getArtPostDate());
        artPostVO.setUserId(artPostDTO.getUserId());
        artPostDAO.save(artPostVO);
    }

//    작품 게시글 전체 조회
    @Override
    public List<ArtPostDTO> getArtPostList() {
        return artPostDAO.findAll();
    }

//    작품 게시글 단일 조회
    @Override
    public Optional<ArtPostDTO> getArtPostById(Long id) {
        return artPostDAO.findById(id);
    }

//    작품 게시글 수정
    @Override
    public void edit(ArtPostVO artPostVO) {
        artPostDAO.update(artPostVO);
    }

//    작품 게시글 삭제 (art + artImg 삭제)
//    @Override
//    public void removeById(Long id) {
//        artPostDAO.findById(id).ifPresent(post -> {
//            Long artId = post.getArtId();
//            artPostDAO.deleteById(id);
//            artImgDAO.deleteAllByArtId(artId);
//            artDAO.deleteById(artId);
//        });
//    }
@Override
public void removeById(Long id) {
    System.out.println("🔍 게시글 삭제 요청됨. postId = " + id);

    artPostDAO.findById(id).ifPresentOrElse(post -> {
        Long artId = post.getArtId();
        System.out.println("✅ 게시글 존재. artId = " + artId);

        System.out.println("🗑 댓글 삭제 시작 (postId = " + id + ")");
        commentDAO.deleteAllByPostId(id);

        System.out.println("🗑 게시글 삭제 시작 (postId = " + id + ")");
        artPostDAO.deleteById(id);

        System.out.println("🗑 이미지 삭제 시작 (artId = " + artId + ")");
        artImgDAO.deleteAllByArtId(artId);

        System.out.println("🗑 작품 삭제 시작 (artId = " + artId + ")");
        artDAO.deleteById(artId);

        System.out.println("✅ 게시글 + 이미지 + 작품 삭제 완료");

    }, () -> {
        System.out.println("⚠ 게시글이 존재하지 않음. postId = " + id);
    });
}


//    작품 게시글 전체 삭제 (회원 탈퇴)
//    @Override
//    public void removeAllByUserId(Long userId) {
//        artPostDAO.deleteAllByUserId(userId);
//    }
}
