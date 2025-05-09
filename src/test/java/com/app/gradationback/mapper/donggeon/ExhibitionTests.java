package com.app.gradationback.mapper.donggeon;

import com.app.gradationback.domain.DisplayDTO;
import com.app.gradationback.domain.GradationExhibitionImgVO;
import com.app.gradationback.domain.GradationExhibitionVO;
import com.app.gradationback.mapper.ExhibitionMapper;
import com.app.gradationback.service.ExhibitionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class ExhibitionTests {

    @Autowired
    private ExhibitionService exhibitionService;

//    전시회 정보 불러오기
    @Test
    public void getGradationTest() {
        exhibitionService.getGradation();
        log.info(exhibitionService.getGradation().toString());
    }

//    전시회 장소 이미지 불러오기
    @Test
    public void getGradationImgTest() {
        List<GradationExhibitionImgVO> images = exhibitionService.getGradationImgAll(2L);
        images.forEach(image -> log.info(image.toString()));
    }

//    전시회 등록
    @Test
    public void registerGradationTest() {
        GradationExhibitionVO gradationExhibitionVO = new GradationExhibitionVO();
        gradationExhibitionVO.setGradationExhibitionTitle("테스트 제목5");
        gradationExhibitionVO.setGradationExhibitionArt("대학교 학생들의 졸업전시품 TOP100");
        gradationExhibitionVO.setGradationExhibitionCategory("한국화, 회화, 공예 등 100점");
        gradationExhibitionVO.setGradationExhibitionTime("10:00 ~ 18:00(입장 마감 17:30)");
        gradationExhibitionVO.setGradationExhibitionFee("성인 17,000원 / 청소년 10,000원 / 어린이 7,000");
        gradationExhibitionVO.setGradationExhibitionTel("02-585-8999");
        gradationExhibitionVO.setGradationExhibitionAddress("디지털 플라자");
        gradationExhibitionVO.setGradationExhibitionDate("2025.05.10 - 2025.06.10");

        exhibitionService.registerGradation(gradationExhibitionVO);
    }

//    전시회 장소 이미지 등록
    @Test
    public void registerGradationImage() {
        GradationExhibitionImgVO gradationExhibitionImgVO = new GradationExhibitionImgVO();
        gradationExhibitionImgVO.setGradationExhibitionId(2L);
        gradationExhibitionImgVO.setGradationExhibitionImgName("전시회2/img3.jpg");
        gradationExhibitionImgVO.setGradationExhibitionImgPath("public/images/gradation");
    
        exhibitionService.registerGradationImage(gradationExhibitionImgVO);
    }


//    전시회 정보 수정
    @Test
    public void updateGradationTest() {
        Long gradationExhibitionId = 2L;
        GradationExhibitionVO gradationExhibitionVO = new GradationExhibitionVO();
        gradationExhibitionVO.setId(gradationExhibitionId);
        gradationExhibitionVO.setGradationExhibitionTitle("테스트 제목2");
        gradationExhibitionVO.setGradationExhibitionArt("대학교 학생들의 졸업전시품 TOP50");
        gradationExhibitionVO.setGradationExhibitionCategory("한국화, 회화, 공예 등 50점");
        gradationExhibitionVO.setGradationExhibitionTime("10:00 ~ 18:00(입장 마감 17:30)");
        gradationExhibitionVO.setGradationExhibitionFee("성인 17,000원 / 청소년 10,000원 / 어린이 7,000");
        gradationExhibitionVO.setGradationExhibitionTel("02-585-8999");
        gradationExhibitionVO.setGradationExhibitionAddress("제주도 대형카페");
        gradationExhibitionVO.setGradationExhibitionDate("2022.05.10 - 2022.06.10");

        exhibitionService.editGradation(gradationExhibitionVO);
    }

//    전시회 장소 이미지 삭제
    @Test
    public void deleteGradationTest() {
        Long imageId = 8L;
        exhibitionService.removeGradationImage(imageId);
    }

//    최근 전시회 3개 조회
    @Test
    public void getRecentGradationsTest() {
        List<GradationExhibitionVO> recentGradations = exhibitionService.getRecentGradations();
        recentGradations.forEach(gradation -> log.info("{} {}", gradation.getGradationExhibitionDate(), gradation.getGradationExhibitionTitle()));
    }

//    좋아요순 작품 50개
    @Test
    public void getTopLikedArtsTest() {
        List<DisplayDTO> topLikedArts = exhibitionService.getTopLikedArts();

        DisplayDTO top1 = topLikedArts.get(0);
        log.info("이미지 {}, {}", top1.getArtImgName(), top1.getArtImgPath());
        log.info("작품 제목 {}", top1.getArtTitle());
        log.info("작가 이름 {}", top1.getUserName());

        DisplayDTO top2 = topLikedArts.get(1);
        log.info("이미지 {}, {}", top2.getArtImgName(), top2.getArtImgPath());
        log.info("작품 제목 {}", top2.getArtTitle());
        log.info("작가 이름 {}", top2.getUserName());


    }


}
