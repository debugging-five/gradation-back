package com.app.gradationback.mapper.mina;

import com.app.gradationback.domain.ArtLikeVO;
import com.app.gradationback.domain.ArtVO;
import com.app.gradationback.mapper.ArtMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
@Slf4j
public class ArtLikeMapperTests {

    @Autowired
    private ArtMapper artMapper;

//   좋아요
    @Test
    public void insertLike() {
        ArtLikeVO artLikeVO = new ArtLikeVO();
        artLikeVO.setUserId(3L);
        artLikeVO.setArtId(2L);
        artLikeVO.setArtLikeTime(new Timestamp(System.currentTimeMillis()));
        artMapper.insertLike(artLikeVO);
        log.info("{}", artLikeVO);
    }

//    좋아요 수
    @Test
    public void selectLikeCount() {
        Long artId = 1L;
        int likeCount = artMapper.selectLikeCount(artId);
        log.info("{}", likeCount);
    }

//    좋아요 삭제
    @Test
    public void deleteLike() {
        ArtLikeVO artLikeVO = new ArtLikeVO();
        artLikeVO.setUserId(3L);
        artLikeVO.setArtId(2L);
        artMapper.deleteLike(artLikeVO);
        log.info("{}", artLikeVO);

    }

}
