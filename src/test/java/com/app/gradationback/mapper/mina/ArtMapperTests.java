package com.app.gradationback.mapper.mina;

import com.app.gradationback.domain.ArtVO;
import com.app.gradationback.mapper.ArtMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
@Slf4j
public class ArtMapperTests {

    @Autowired
    private ArtMapper artMapper;

//    작품 등록
    @Test
    public void insertTest() {
        ArtVO artVO = new ArtVO();
        artVO.setArtTitle("작품작품");
        artVO.setArtCategory("카테고리카테고리");
        artVO.setArtDescription("설명설명");
        artVO.setArtSize("700 x 700 x 700");
        artVO.setArtMaterial("재료재료");
        artVO.setUserId(95L);
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date endDate = formatter.parse("2025-05-05");
            artVO.setArtEndDate(endDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        artMapper.insert(artVO);
        log.info("{}", artVO);
    }

//    전체 작품 조회
    @Test
    public void selectAllTest() {
        List<ArtVO> artVOList = artMapper.selectAll();
        for (ArtVO artVO : artVOList) {
            log.info("{}", artVO);
        }
    }

//    단일 작품 조회
    @Test
    public void selectTest() {
        ArtVO artVO = new ArtVO();
        artVO.setId(401L);
        artMapper.select(artVO.getId()).map(ArtVO::toString).ifPresent(log::info);
    }

//    카테고리별 작품 조회
//    @Test
//    public void selectArtListByFilterTest() {
//        ArtFilterVO artFilterVO = new ArtFilterVO();
//        artFilterVO.setArtCategory("건축");
//        artFilterVO.setOffset(0);
//        artFilterVO.setLimit(15);
//        artFilterVO.setSortBy("date");
//        List<ArtVO> artVOList = artMapper.selectArtListByFilter(artFilterVO);
//
//        for (ArtVO artVO : artVOList) {
//            log.info("{}", artVO);
//        }
//    }

//    카테고리 + 드롭다운 + 페이지네이션
    @Test
    public void selectArtByCategoryAndDropdown() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("artCategory", "서예");
        params.put("cursor", 1);
//        params.put("sortBy", "likes");
        params.put("sortBy", "date");

        artMapper.selectArtListByCategoryAndDropdown(params)
                .stream().map(ArtVO::toString).forEach(log::info);

    }

//    작품 삭제
    @Test
    public void deleteTest() {
        artMapper.deleteById(401L);
    }
}
