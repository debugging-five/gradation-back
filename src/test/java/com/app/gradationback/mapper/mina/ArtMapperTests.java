package com.app.gradationback.mapper.mina;

import com.app.gradationback.domain.ArtVO;
import com.app.gradationback.mapper.ArtMapper;
import com.app.gradationback.mapper.ArtPostMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Slf4j
public class ArtMapperTests {

    @Autowired
    private ArtMapper artMapper;

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
}
