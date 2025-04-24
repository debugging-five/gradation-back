package com.app.gradationback.mapper.mina;

import com.app.gradationback.domain.ArtImgVO;
import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.domain.ArtPostVO;
import com.app.gradationback.domain.ArtVO;
import com.app.gradationback.mapper.ArtImgMapper;
import com.app.gradationback.mapper.ArtMapper;
import com.app.gradationback.mapper.ArtPostMapper;
import com.app.gradationback.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
    private ArtImgVO artImgVO;
    @Autowired
    private UserMapper userMapper;

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

//    작품 게시 삭제 (user만들고 다시)
    public void deleteArtPostTest() {
        artPostMapper.delete(7L);
        artMapper.delete(1L);
    }


}
