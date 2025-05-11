package com.app.gradationback.mapper.donggeon;

import com.app.gradationback.domain.*;
import com.app.gradationback.mapper.ExhibitionMapper;
import com.app.gradationback.service.ExhibitionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Slf4j
public class ExhibitionTests {

    @Autowired
    private ExhibitionService exhibitionService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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


//    University
//    신청 양식(대학교 + 학과 + 대학교 전시회 + 대학교 전시회 이미지)
//    해당학교가 DB 없을때

    @Test
    public void registerNoneUniversityTest() {
        UniversityExhibitionDTO universityExhibitionDTO = new UniversityExhibitionDTO();
        universityExhibitionDTO.setUniversityName("테스트 대학교");
        universityExhibitionDTO.setUniversityHomepage("https://www.banggooso.com");

        universityExhibitionDTO.setMajorName("테스트학과");

        universityExhibitionDTO.setUniversityExhibitionTitle("제 1회 졸업전시회");
        universityExhibitionDTO.setUniversityExhibitionLocation("조형관 2층");
        universityExhibitionDTO.setUniversityExhibitionStartDate(new Date());
        universityExhibitionDTO.setUniversityExhibitionEndDate(new Date());

        universityExhibitionDTO.setUniversityExhibitionImgName("테스트대학교 작년 전시회 사진.jpg");
        universityExhibitionDTO.setUniversityExhibitionImgPath("assets/images/university");

        if (universityExhibitionDTO.getUniversityLogoImgName() == null) {
            universityExhibitionDTO.setUniversityLogoImgName("default-logo.png");
        }
        if (universityExhibitionDTO.getUniversityLogoImgPath() == null) {
            universityExhibitionDTO.setUniversityLogoImgPath("public/images/default");
        }

        exhibitionService.registerUniversity(universityExhibitionDTO);
    }

//    해당학교가 DB 있을때
    @Test
    public void registerUniversityTest() {
        UniversityExhibitionDTO universityExhibitionDTO = new UniversityExhibitionDTO();
        universityExhibitionDTO.setUniversityName("고려대학교");
        universityExhibitionDTO.setUniversityHomepage("https://www.banggooso.com");

        universityExhibitionDTO.setMajorName("테스트학과");

        universityExhibitionDTO.setUniversityExhibitionTitle("제 2회 졸업전시");
        universityExhibitionDTO.setUniversityExhibitionLocation("체육관 1F");
        try {
            universityExhibitionDTO.setUniversityExhibitionStartDate(sdf.parse("2025-11-24"));
            universityExhibitionDTO.setUniversityExhibitionEndDate(sdf.parse("2025-11-30"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        universityExhibitionDTO.setUniversityExhibitionImgName("고려대 테스트 이미지2.jpg");
        universityExhibitionDTO.setUniversityExhibitionImgPath("assets/images/university");

        exhibitionService.registerUniversity(universityExhibitionDTO);
    }

//    대학교 전시회 조회
    @Test
    public void getUniversityTest() {
        exhibitionService.getUniversity();
        log.info(exhibitionService.getUniversity().toString());
    }

//    대학 전시회 이미지 조회
    @Test
    public void getUniversityImgTest() {
        List<UniversityExhibitionImgVO> images = exhibitionService.getUniversityImgAll(6L);
        images.forEach(image -> log.info(image.toString()));
    }






}









