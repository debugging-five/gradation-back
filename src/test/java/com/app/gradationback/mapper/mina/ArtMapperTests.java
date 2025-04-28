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
        artVO.setArtTitle("작품6");
        artVO.setArtCategory("카테고리6");
        artVO.setArtDescription("설명6");
        artVO.setArtSize("600 x 600 x 600");
        artVO.setArtMaterial("재료6");
        artVO.setUserId(88L);
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date endDate = formatter.parse("2025-04-28");
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
        artVO.setId(384L);
        artMapper.select(artVO.getId()).map(ArtVO::toString).ifPresent(log::info);
    }

//    작품 정보 수정
    @Test
    public void updateTest() {
        ArtVO artVO = new ArtVO();
        artVO.setId(384L);
        artVO.setArtTitle("수정된 작품");
        artVO.setArtCategory("카테고리4");
        artVO.setArtDescription("수정된 설명4");
        artVO.setArtMaterial("재료4");
        artVO.setArtSize("600 x 600 x 600");
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date endDate = formatter.parse("2025-04-27");
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
        artMapper.delete(384L);
    }
}
