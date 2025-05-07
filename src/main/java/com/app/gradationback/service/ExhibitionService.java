package com.app.gradationback.service;

import com.app.gradationback.domain.GradationExhibitionDTO;
import com.app.gradationback.domain.GradationExhibitionImgVO;
import com.app.gradationback.domain.GradationExhibitionVO;

import java.util.List;
import java.util.Optional;

public interface ExhibitionService {

//    그라데이션
//    전시회 정보 불러오기
    public Optional<GradationExhibitionVO> getGradation();

//    전시관 장소 이미지 불러오기
    public List<GradationExhibitionImgVO> getGradationImgAll(Long gradationExhibitionId);

//    전시회 등록
    public void registerGradation(GradationExhibitionVO gradationExhibitionVO);

//    전시회 장소 이미지 추가
    public void registerGradationImage(GradationExhibitionImgVO gradationExhibitionImgVO);

//    전시회 정보 수정
    public void editGradation(GradationExhibitionVO gradationExhibitionVO);

//    전시회 장소 이미지 삭제
    public void removeGradationImage(Long id);

//    최근 전시회 3개 조회
    public List<GradationExhibitionVO> getRecentGradations();


}



