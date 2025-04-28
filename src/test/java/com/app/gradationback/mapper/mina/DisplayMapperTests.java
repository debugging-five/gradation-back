package com.app.gradationback.mapper.mina;

import com.app.gradationback.domain.*;
import com.app.gradationback.mapper.*;
import com.app.gradationback.service.DisplayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Slf4j

public class DisplayMapperTests {

    @Autowired
    private ArtMapper artMapper;

    @Autowired
    private ArtImgMapper artImgMapper;

    @Autowired
    private ArtPostMapper artPostMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ArtImgVO artImgVO;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DisplayService displayService;

    //    ArtMapper
    //    작품 정보 등록
    @Test
    public void insertTest() {
        ArtVO artVO = new ArtVO();
        artVO.setArtTitle("작품1");
        artVO.setArtCategory("카테고리1");
        artVO.setArtDescription("설명1");
        artVO.setArtSize("300X300X300");
        artVO.setArtMaterial("재료1");
        artVO.setUserId(1L);
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date endDate = formatter.parse("2025-04-24");
            artVO.setArtEndDate(endDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        artMapper.insert(artVO);
        log.info("{}", artVO);
    }

//    전체 작품 정보 조회
    @Test
    public void selectAllTest() {
        List<ArtVO> artVOList = artMapper.selectAll();
        for (ArtVO artVO : artVOList) {
            log.info("{}", artVO);
        }
    }

//    단일 작품 정보 조회
    @Test
    public void selectTest() {
        ArtVO artVO = new ArtVO();
        artVO.setArtTitle("작품2");
        artVO.setArtCategory("카테고리2");
        artVO.setArtDescription("설명2");
        artVO.setArtSize("200X200X200");
        artVO.setArtMaterial("재료2");
        artVO.setUserId(2L);
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date endDate = formatter.parse("2025-04-24");
            artVO.setArtEndDate(endDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        artMapper.select(2L);
        log.info("{}", artVO);
    }

//    작품 정보 수정
    @Test
    public void updateTest() {
        ArtVO artVO = new ArtVO();
        artVO.setId(381L);
        artVO.setArtTitle("수정된 작품1");
        artVO.setArtCategory("수정된 카테고리1");
        artVO.setArtDescription("수정된 설명1");
        artVO.setArtSize("300X300X300");
        artVO.setArtMaterial("재료1");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date endDate = formatter.parse("2025-04-24");
            artVO.setArtEndDate(endDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        artMapper.update(artVO);
        log.info("{}", artVO);
    }

//    작품 정보 삭제
    @Test
    public void deleteTest() {
        artMapper.delete(381L);
    }

//    ===================================================================================================
//    ArtImgMapper
//    작품 이미지 등록
    @Test
    public void insertArtImgTest() {
        ArtImgVO artImgVO = new ArtImgVO();
        artImgVO.setArtImgName("작품 이미지1");
        artImgVO.setArtImgPath("/images/art1");
        artImgVO.setArtId(1L);
        artImgMapper.insert(artImgVO);
        log.info("{}", artImgVO);
    }

//    전체 작품 이미지 조회
    @Test
    public void selectArtImgAllTest() {
        List<ArtImgVO> artImgVOList = artImgMapper.selectAll();
        for (ArtImgVO artImgVO : artImgVOList) {
            log.info("{}", artImgVO);
        }
    }

//    단일 작품 이미지 조회
    @Test
    public void selectArtImgTest() {
        ArtImgVO artImgVO = new ArtImgVO();
        artImgVO.setId(381L);
        artImgMapper.select(artImgVO.getId()).map(ArtImgVO::toString).ifPresent(log::info);
    }

//    작품 이미지 수정
    @Test
    public void updateArtImgTest() {
        ArtImgVO artImgVO = new ArtImgVO();
        artImgVO.setId(381L);
        artImgVO.setArtImgName("수정된 작품 이미지1");
        artImgVO.setArtImgPath("/images/art1");
        artImgMapper.update(artImgVO);
        log.info("{}", artImgVO);
    }

//    작품 이미지 삭제
    @Test
    public void deleteArtImgTest() {
        artImgMapper.delete(381L);
    }

    //    ===================================================================================================
//    ArtPostMapper
//    작품 게시
    @Test
    public void insertArtPostTest() {
        ArtPostVO artPostVO = new ArtPostVO();
        artPostVO.setArtPostDate(new Timestamp(System.currentTimeMillis()));
        artPostVO.setUserId(1L);
        artPostVO.setArtId(1L);
        artPostMapper.insert(artPostVO);
        log.info("{}", artPostVO);
    }

//    작품 게시 조회 (전체)
    @Test
    public void selectArtPostAllTest() {
        List<ArtPostDTO> artPostList = artPostMapper.selectAll();
        for (ArtPostDTO artPostDTO : artPostList) {
            log.info("{}", artPostDTO);
        }
    }

//    작품 게시 조회(단일)
    @Test
    public void selectArtPostTest() {
        ArtPostDTO artPostDTO = new ArtPostDTO();
        artPostDTO.setId(3L);
        artPostMapper.select(artPostDTO.getId()).map(ArtPostDTO::toString).ifPresent(log::info);
    }

//    작품 게시 수정
    @Test
    public void updateArtPostTest() {
        ArtPostVO artPostVO = new ArtPostVO();
        artPostVO.setId(3L);
        artPostVO.setArtPostDate(new Timestamp(System.currentTimeMillis()));
        artPostMapper.update(artPostVO);
        log.info("{}", artPostVO);
    }

//    작품 게시 삭제 (유저 필요)
    @Test
    public void deleteArtPostTest() {
        artPostMapper.delete(21L);
//        artMapper.delete(1L);
    }

//    작품 게시 전체 삭제 (회원 탈퇴)

    //    ===================================================================================================
//    CommentMapper
//    댓글 등록
    @Test
    public void insertReplyTest() {
        CommentVO commentVO = new CommentVO();
        commentVO.setArtPostId(3L);
        commentVO.setUserId(64L);
        commentVO.setCommentContent("유저3의 댓글2");
        commentVO.setCommentDate(new Timestamp(System.currentTimeMillis()));
        commentMapper.insert(commentVO);
        log.info("{}", commentVO);
    }

//    댓글 전체 조회
    @Test
    public void selectAllReplyTest() {
        List<CommentVO> commentVOList = commentMapper.selectAll();
        for (CommentVO commentVO : commentVOList) {
            log.info("{}", commentVO);
        }
    }

//    댓글 단일 조회
    @Test
    public void selectReplyTest() {
        CommentVO commentVO = new CommentVO();
        commentVO.setId(23L);
        commentMapper.select(commentVO.getId()).map(CommentVO::toString).ifPresent(log::info);
    }

//    댓글 수정
    @Test
    public void updateReplyTest() {
        CommentVO commentVO = new CommentVO();
        commentVO.setId(23L);
        commentVO.setCommentContent("수정된 댓글댓글");
        commentVO.setCommentDate(new Timestamp(System.currentTimeMillis()));
        commentMapper.update(commentVO);
        log.info("{}", commentVO);
    }

//    댓글 삭제
    @Test
    public void deleteReplyTest() {
        commentMapper.delete(23L);
    }

//    댓글 전체 삭제 (회원 탈퇴)
    @Test
    public void deleteAllReplyByUserIdTest() {
        commentMapper.deleteAllByUserId(58L);
    }

//    댓글 전체 삭제 (게시글 삭제)
    @Test
    public void deleteAllReplyByPostIdTest() {
        commentMapper.deleteAllByPostId(5L);
        artPostMapper.delete(5L);
    }

//    DisplayService
//    @Test
//    public void test() {
//        ArtPostDTO artPostDTO = new ArtPostDTO();
//        artPostDTO.setArtTitle("제목1");
//        artPostDTO.setArtCategory("카테고리1");
//        artPostDTO.setArtDescription("설명1");
//        artPostDTO.setArtMaterial("재료1");
//        artPostDTO.setArtImgPath("/images/art1");
//        artPostDTO.setArtSize("10x10x10");
//        artPostDTO.setArtEndDate(new Timestamp(System.currentTimeMillis()));
//        artPostDTO.getArtImgName("이미지1");
//        artPostDTO.setUserId(45L);
//        displayService.registerForm(artPostDTO);
//        log.info("{}", artPostDTO);
//    }

//    ===================================================================================================


}
