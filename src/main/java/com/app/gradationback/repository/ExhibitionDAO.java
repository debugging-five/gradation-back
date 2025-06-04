package com.app.gradationback.repository;

import com.app.gradationback.domain.*;
import com.app.gradationback.mapper.ExhibitionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ExhibitionDAO {

    private final ExhibitionMapper exhibitionMapper;

//    그라데이션
//    전시회 정보 불러오기
    public Optional<GradationExhibitionVO> findGradation() {
        return exhibitionMapper.selectGradation();
    }

//    전시관 장소 이미지 불러오기
    public List<GradationExhibitionImgVO> findGradationImgAll(Long gradationExhibitionId) {
        return exhibitionMapper.selectGradationImgAll(gradationExhibitionId);
    }

//    전시회 등록
    public void saveGradation(GradationExhibitionVO gradationExhibitionVO) {
        exhibitionMapper.insertGradation(gradationExhibitionVO);
    }

//    전시회 장소 이미지 추가
    public void saveGradationImage(GradationExhibitionImgVO gradationExhibitionImgVO) {
        exhibitionMapper.insertGradationImg(gradationExhibitionImgVO);
    }

    //    올해 좋아요 50개 작품 id값
    public List<Long> findTop50ArtId() {
        return exhibitionMapper.selectTop50ArtId();
    }
    //    지난 전시회 등록
    public void savePastExhibition(Map<String, Object> params) {
        exhibitionMapper.insertPastGradationArt(params);
    }

    //    전시회 정보 수정
    public void updateGradation(GradationExhibitionVO gradationExhibitionVO) {
        exhibitionMapper.updateGradation(gradationExhibitionVO);
    }

//    전시회 장소 이미지 삭제
    public void deleteGradationImage(Long id) {
        exhibitionMapper.deleteGradationImg(id);
    }

//    최근 전시회 3개 조회
    public List<GradationExhibitionVO> findRecentGradations() {
        return exhibitionMapper.selectRecentGradations();
    }

//    좋아요순 50개 작품
    public List<DisplayDTO> findTopLikedArts() {
        return exhibitionMapper.selectTopLikedArts();
    }

//    지난 전시회 리스트
    public List<ExhibitionPastDTO> findPastExhibitions() {
        return exhibitionMapper.selectPastExhibitions();
    }

//    지난 전시회 작품 리스트
    public List<ExhibitionPastDTO> findExhibitionArtList(Map<String, Object> params) {
        return exhibitionMapper.selectExhibitionArtList(params);
    }

    public Integer findCountExhibitionArtList(Long exhibitionId) {
        return exhibitionMapper.selectExhibitionCountArtList(exhibitionId);
    }

//    대학교 전시회
//    신청 양식(대학교)
    public void saveUniversity(UniversityExhibitionDTO universityExhibitionDTO) {
        exhibitionMapper.insertUniversity(universityExhibitionDTO);
    }

//    신청 양식(학과)
    public void saveMajor(UniversityExhibitionDTO universityExhibitionDTO){
        exhibitionMapper.insertMajor(universityExhibitionDTO);
    }

//    신청 양식(대학교 전시회)
    public void saveUniversityExhibition(UniversityExhibitionDTO universityExhibitionDTO) {
        exhibitionMapper.insertUniversityExhibition(universityExhibitionDTO);
    }

//    신청 양식(대학교 전시회 이미지)
    public void saveUniversityExhibitionImg(UniversityExhibitionDTO universityExhibitionDTO) {
        exhibitionMapper.insertUniversityExhibitionImg(universityExhibitionDTO);
    }

//    대학교 조회
    public Optional<UniversityVO> findUniversityByName(String universityName) {
        return exhibitionMapper.findUniversityByName(universityName);
    }

//    대학 전시회 정보 조회
    public List<UniversityExhibitionDTO> findUniversity(Map<String, Object> params) {
        return exhibitionMapper.selectUniversity(params);
    }

//    대학 전시회 이미지 조회
    public List<UniversityExhibitionImgVO> findUniversityImgAll(Long universityExhibitionId) {
        return exhibitionMapper.selectUniversityImgAll(universityExhibitionId);
    }

//    좋아요 등록
    public void saveUniversityLike(UniversityLikeVO universityLikeVO) {
        exhibitionMapper.insertUniversityLike(universityLikeVO);
    }

//    좋아요 여부
    public Integer findUniversityLike(UniversityLikeVO universityLikeVO) {
        return exhibitionMapper.selectUniversityLike(universityLikeVO);
    }

//    좋아요 취소
    public void deleteUniversityLike(UniversityLikeVO universityLikeVO) {
        exhibitionMapper.deleteUniversityLike(universityLikeVO);
    }

//      내가 신청한 전시회 승인내역 조회
    public List<UniversityExhibitionDTO> findMyExhibitionStatus(Long userId) {
        return exhibitionMapper.selectExhibitionStatus(userId);
    }

//      내가 좋아요 한 전시회 목록
    public List<UniversityExhibitionDTO> findLikedUniversityExhibition(Long userId) {
        return exhibitionMapper.selectLikedUniversityExhibition(userId);
    }


}








