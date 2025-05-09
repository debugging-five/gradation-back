package com.app.gradationback.mapper.mina;

import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.domain.ArtPostVO;
import com.app.gradationback.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
@Slf4j
public class ArtPostMapperTests {

    @Autowired
    private ArtPostMapper artPostMapper;
    @Autowired
    private ArtImgMapper artImgMapper;
    @Autowired
    private ArtMapper artMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;

    //    작품 게시글 등록
    @Test
    public void insertArtPostTest() {
        ArtPostVO artPostVO = new ArtPostVO();
        artPostVO.setArtPostDate(new Timestamp(System.currentTimeMillis()));
        artPostVO.setUserId(7L);
        artPostVO.setArtId(46L);
        artPostMapper.insert(artPostVO);
        log.info("{}", artPostVO);
    }

//    작품 게시글 전체 조회
    @Test
    public void selectArtPostAllTest() {
        List<ArtPostDTO> artPostList = artPostMapper.selectAll();
        for (ArtPostDTO artPostDTO : artPostList) {
            log.info("{}", artPostDTO);
        }
    }

//    작품 게시글 단일 조회
    @Test
    public void selectArtPostTest() {
        ArtPostDTO artPostDTO = new ArtPostDTO();
        artPostDTO.setId(3L);
        artPostMapper.select(artPostDTO.getId()).map(ArtPostDTO::toString).ifPresent(log::info);
    }

//    작품 게시글 수정
    @Test
    public void updateArtPostTest() {
        ArtPostVO artPostVO = new ArtPostVO();
        artPostVO.setId(5L);
        artPostVO.setArtPostDate(new Timestamp(System.currentTimeMillis()));
        artPostMapper.update(artPostVO);
        log.info("{}", artPostVO);
    }

//    작품 게시글 삭제
    @Test
    public void deleteArtPostTest() {
        commentMapper.deleteAllByPostId(23L);
        artPostMapper.deleteById(23L);
        artImgMapper.deleteAllByArtId(421L);
        artMapper.deleteById(421L);
    }

//    작품 게시글 전체 삭제 (회원 탈퇴시)
    @Test
    public void deleteAllArtPostTest() {
        artPostMapper.deleteAllByUserId(88L);
        artImgMapper.deleteAllByArtId(385L);
        artMapper.deleteById(385L);
        userMapper.deleteUser("user@test.app");
    }


}
