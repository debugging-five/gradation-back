package com.app.gradationback.service;

import com.app.gradationback.domain.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ExhibitionService {

//    그라데이션
//    전시회 정보 불러오기
    public Optional<GradationExhibitionVO> getGradation();

//    전시관 장소 이미지 불러오기
    public List<GradationExhibitionImgVO> getGradationImgAll(Long gradationExhibitionId);

//    전시회 등록(전시회 정보 + 작품 50개 가져오기 + 지난 전시회 등록)
    public GradationExhibitionVO registerGradation(GradationExhibitionDTO gradationExhibitionDTO);
//    지난 전시회 등록
//    public void registerPastGradationArt(Map<String, Object> params);
//    전시회 장소 이미지 추가
    public void registerGradationImage(GradationExhibitionImgVO gradationExhibitionImgVO);

//    전시회 정보 수정
    public void editGradation(GradationExhibitionVO gradationExhibitionVO);

//    전시회 장소 이미지 삭제
    public void removeGradationImage(Long id);

//    최근 전시회 3개 조회
    public List<GradationExhibitionVO> getRecentGradations();

//    좋아요순 50개 작품
    public List<DisplayDTO> getTopLikedArts();

//    지난 전시회 리스트
    public List<ExhibitionPastDTO> getPastExhibitions();

//    지난 전시회 작품 리스트
    public List<ExhibitionPastDTO> getExhibitionArtList(Map<String, Object> params);

//    대학교 전시회
//    신청 양식(대학교 + 학과 + 대학교 전시회 + 대학교 전시회 이미지)
    public void registerUniversity(UniversityExhibitionDTO universityExhibitionDTO);

//    대학 젼시회 정보 조회
    public List<UniversityExhibitionDTO> getUniversity(UniversityExhibitionDTO universityExhibitionDTO);

//    전시회 이미지
    public List<UniversityExhibitionImgVO> getUniversityImgAll(Long universityExhibitionId);

//    대학교 좋아요
    public void registerUniversityLike(UniversityLikeVO universityLikeVO);

//    좋아요 취소
    public void removeUniversityLike(UniversityLikeVO universityLikeVO);

}






