package com.app.gradationback.service;

import com.app.gradationback.domain.GradationExhibitionDTO;
import com.app.gradationback.domain.GradationExhibitionImgVO;
import com.app.gradationback.domain.GradationExhibitionVO;

import java.util.List;

public interface ExhibitionService {

    //    그라데이션
//    전시회 정보 화면에 불러오기
    public List<GradationExhibitionDTO> getGradation();

    //    전시회 등록
    public void registerGradation(GradationExhibitionVO gradationExhibitionVO, List<GradationExhibitionImgVO> gradationExhibitionImgVO);

    //    전시회 정보 수정
    public void editGradation(GradationExhibitionVO gradationExhibitionVO);

    //    전시회 장소 이미지 추가
    public void registerGradationImage(GradationExhibitionImgVO gradationExhibitionImgVO);

    //    전시회 장소 이미지 삭제
    public void removeGradationImage(Long id);



}



