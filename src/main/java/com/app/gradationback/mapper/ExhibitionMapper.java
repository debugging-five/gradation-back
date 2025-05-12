package com.app.gradationback.mapper;

import com.app.gradationback.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ExhibitionMapper {

//    그라데이션
//    전시회 정보 불러오기
    public Optional<GradationExhibitionVO> selectGradation();

//    전시관 장소 이미지 불러오기
    public List<GradationExhibitionImgVO> selectGradationImgAll(Long gradationExhibitionId);

//    전시회 등록
    public void insertGradation(GradationExhibitionVO gradationExhibitionVO);

//    전시회 장소 이미지 추가
    public void insertGradationImg(GradationExhibitionImgVO gradationExhibitionImgVO);

//    전시회 정보 수정
    public void updateGradation(GradationExhibitionVO gradationExhibitionVO);

//    전시회 장소 이미지 삭제
    public void deleteGradationImg(Long id);

//    최근 전시회 3개 조회
    public List<GradationExhibitionVO> selectRecentGradations();

//    좋아요순 50개 작품
    public List<DisplayDTO> selectTopLikedArts();


//    대학교 전시회
//    신청 양식(대학교)
    public void insertUniversity(UniversityExhibitionDTO universityExhibitionDTO);

//    신청 양식(학과)
    public void insertMajor(UniversityExhibitionDTO universityExhibitionDTO);

//    신청 양식(대학교 전시회)
    public void insertUniversityExhibition(UniversityExhibitionDTO universityExhibitionDTO);

//    신청 양식(대학교 전시회 이미지)
    public void insertUniversityExhibitionImg(UniversityExhibitionDTO universityExhibitionDTO);

//    대학교 조회
    public Optional<UniversityVO> findUniversityByName(String universityName);

//    전시회 정보 조회
    public List<UniversityExhibitionDTO> selectUniversity(UniversityExhibitionDTO universityExhibitionDTO);

//    해당 전시회 이미지 띄우기
    public List<UniversityExhibitionImgVO> selectUniversityImgAll(Long universityExhibitionId);

//    좋아요
    public void insertUniversityLike(UniversityLikeVO universityLikeVO);

//    좋아요 취소
    public void deleteUniversityLike(UniversityLikeVO universityLikeVO);


}






