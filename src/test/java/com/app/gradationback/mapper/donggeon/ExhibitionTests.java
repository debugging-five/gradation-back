package com.app.gradationback.mapper.donggeon;

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
        gradationExhibitionVO.setGradationExhibitionTitle("테스트 제목3");
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
        gradationExhibitionImgVO.setGradationExhibitionImgName("img1.jpg");
        gradationExhibitionImgVO.setGradationExhibitionImgPath("public/images/gradation");
    
        exhibitionService.registerGradationImage(gradationExhibitionImgVO);
    }


//    전시회 정보 수정
    @Test
    public void updateGradationTest() {
        Long gradationExhibitionId = 1L;
        GradationExhibitionVO gradationExhibitionVO = new GradationExhibitionVO();
        gradationExhibitionVO.setId(gradationExhibitionId);
        gradationExhibitionVO.setGradationExhibitionTitle("테스트 제목1");
        gradationExhibitionVO.setGradationExhibitionArt("대학교 학생들의 졸업전시품 TOP50");
        gradationExhibitionVO.setGradationExhibitionCategory("한국화, 회화, 공예 등 50점");
        gradationExhibitionVO.setGradationExhibitionTime("10:00 ~ 18:00(입장 마감 17:30)");
        gradationExhibitionVO.setGradationExhibitionFee("성인 17,000원 / 청소년 10,000원 / 어린이 7,000");
        gradationExhibitionVO.setGradationExhibitionTel("02-585-8999");
        gradationExhibitionVO.setGradationExhibitionAddress("제주도 대형카페");
        gradationExhibitionVO.setGradationExhibitionDate("2025.05.10 - 2025.06.10");

        exhibitionService.editGradation(gradationExhibitionVO);
    }

//    전시회 장소 이미지 삭제
    @Test
    public void deleteGradationTest() {
        Long imageId = 2L;
        exhibitionService.removeGradationImage(imageId);
    }

}
