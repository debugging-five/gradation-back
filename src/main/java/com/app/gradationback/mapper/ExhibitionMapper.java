package com.app.gradationback.mapper;

import com.app.gradationback.domain.GradationExhibitionDTO;
import com.app.gradationback.domain.GradationExhibitionImgVO;
import com.app.gradationback.domain.GradationExhibitionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExhibitionMapper {

    //    그라데이션
//    전시회 정보 화면에 불러오기
    public List<GradationExhibitionDTO> selectGradation();

    //    전시회 등록
    public void insertGradation(GradationExhibitionVO gradationExhibitionVO);

    //    전시회 장소 이미지 추가
    public void insertGradationImg(GradationExhibitionImgVO gradationExhibitionImgVO);

    //    전시회 정보 수정
    public void updateGradation(GradationExhibitionVO gradationExhibitionVO);

    //    전시회 장소 이미지 수정
//    public void updateGradationImg(GradationExhibitionImgVO gradationExhibitionImgVO);

    //    전시회 장소 이미지 삭제
    public void deleteGradationImg(Long id);

}
