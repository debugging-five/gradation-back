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

    @Test
    public void registerGradationTest() {
        GradationExhibitionVO gradationExhibitionVO = new GradationExhibitionVO();
        gradationExhibitionVO.setGradationExhibitionTitle("테스트 제목1");
        gradationExhibitionVO.setGradationExhibitionArt("대학교 학생들의 졸업전시품 TOP100");
        gradationExhibitionVO.setGradationExhibitionCategory("한국화, 회화, 공예 등 100점");
        gradationExhibitionVO.setGradationExhibitionTime("10:00 ~ 18:00(입장 마감 17:30)");
        gradationExhibitionVO.setGradationExhibitionFee("성인 17,000원 / 청소년 10,000원 / 어린이 7,000");
        gradationExhibitionVO.setGradationExhibitionTel("02-585-8999");
        gradationExhibitionVO.setGradationExhibitionAddress("디지털 플라자");
        gradationExhibitionVO.setGradationExhibitionDate("2025.05.10 - 2025.06.10");

        GradationExhibitionImgVO gradationExhibitionImgVO = new GradationExhibitionImgVO();
        gradationExhibitionImgVO.setGradationExhibitionImgName("gradationExhibitionImg.jpg");
        gradationExhibitionImgVO.setGradationExhibitionImgPath("public/images/gradation");

        List<GradationExhibitionImgVO> gradationImageList = List.of(gradationExhibitionImgVO);

        exhibitionService.registerGradation(gradationExhibitionVO, gradationImageList);
    }

    @Test
    public void updateGradationTest() {
        Long gradationExhibitionId = 1L;
        GradationExhibitionVO gradationExhibitionVO = new GradationExhibitionVO();
        gradationExhibitionVO.setId(gradationExhibitionId);
        gradationExhibitionVO.setGradationExhibitionTitle("수정테스트 제목");
        gradationExhibitionVO.setGradationExhibitionArt("대학교 학생들의 졸업전시품 TOP50");
        gradationExhibitionVO.setGradationExhibitionCategory("한국화, 회화, 공예 등 50점");
        gradationExhibitionVO.setGradationExhibitionTime("10:00 ~ 18:00(입장 마감 17:30)");
        gradationExhibitionVO.setGradationExhibitionFee("성인 17,000원 / 청소년 10,000원 / 어린이 7,000");
        gradationExhibitionVO.setGradationExhibitionTel("02-585-8999");
        gradationExhibitionVO.setGradationExhibitionAddress("제주도 대형카페");
        gradationExhibitionVO.setGradationExhibitionDate("2025.05.10 - 2025.06.10");

//        GradationExhibitionImgVO gradationExhibitionImgVO = new GradationExhibitionImgVO();
//        gradationExhibitionImgVO.setId(1L);
//        gradationExhibitionImgVO.setGradationExhibitionImgName("img1.jpg");
//        gradationExhibitionImgVO.setGradationExhibitionImgPath("public/images/gradation");
//
//        List<GradationExhibitionImgVO> gradationImageList = List.of(gradationExhibitionImgVO);

        exhibitionService.editGradation(gradationExhibitionVO);
    }

    @Test
    public void registerGradationImage() {
        GradationExhibitionImgVO gradationExhibitionImgVO = new GradationExhibitionImgVO();
        gradationExhibitionImgVO.setGradationExhibitionId(1L);
        gradationExhibitionImgVO.setGradationExhibitionImgName("img2.jpg");
        gradationExhibitionImgVO.setGradationExhibitionImgPath("public/images/gradation");

        exhibitionService.registerGradationImage(gradationExhibitionImgVO);
    }

    @Test
    public void deleteGradationTest() {
        Long imageId = 2L;
        exhibitionService.removeGradationImage(imageId);
    }

}
