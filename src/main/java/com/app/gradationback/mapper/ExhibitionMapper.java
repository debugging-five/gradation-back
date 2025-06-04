package com.app.gradationback.mapper;

import com.app.gradationback.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
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

//    올해의 좋아요 50개 작품 id값
    public List<Long> selectTop50ArtId();

//    지난 전시회 등록
    public void insertPastGradationArt(Map<String, Object> params);

//    지난 전시회 리스트
    public List<ExhibitionPastDTO> selectPastExhibitions();

//    지난 전시회 작품들
    public List<ExhibitionPastDTO> selectExhibitionArtList(Map<String, Object> params);

    public Integer selectExhibitionCountArtList(Long exhibitionId);


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
    public List<UniversityExhibitionDTO> selectUniversity(Map<String, Object> params);

//    해당 전시회 이미지 띄우기
    public List<UniversityExhibitionImgVO> selectUniversityImgAll(Long universityExhibitionId);

//    좋아요 등록
    public void insertUniversityLike(UniversityLikeVO universityLikeVO);

//    좋아요 여부
    public Integer selectUniversityLike(UniversityLikeVO universityLikeVO);

//    좋아요 취소
    public void deleteUniversityLike(UniversityLikeVO universityLikeVO);

//    내가 신청한 전시회 승인내역 조회
    public List<UniversityExhibitionDTO> selectExhibitionStatus(Long userId);

//    내가 좋아요 한 전시회 목록
    public List<UniversityExhibitionDTO> selectLikedUniversityExhibition(Long userId);
}






