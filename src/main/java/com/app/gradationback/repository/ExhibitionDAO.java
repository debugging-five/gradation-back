package com.app.gradationback.repository;

import com.app.gradationback.domain.GradationExhibitionDTO;
import com.app.gradationback.domain.GradationExhibitionImgVO;
import com.app.gradationback.domain.GradationExhibitionVO;
import com.app.gradationback.mapper.ExhibitionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ExhibitionDAO {

    private final ExhibitionMapper exhibitionMapper;

    //    그라데이션
//    전시회 정보 화면에 불러오기
    public List<GradationExhibitionDTO> findGradationById() {
        return exhibitionMapper.selectGradation();
    }

    //    전시회 등록
    public void saveGradation(GradationExhibitionVO gradationExhibitionVO) {
        exhibitionMapper.insertGradation(gradationExhibitionVO);
    }

    //    전시회 장소 이미지 추가
    public void saveGradationImage(GradationExhibitionImgVO gradationExhibitionImgVO) {
        exhibitionMapper.insertGradationImg(gradationExhibitionImgVO);
    }

    //    전시회 정보 수정
    public void updateGradation(GradationExhibitionVO gradationExhibitionVO) {
        exhibitionMapper.updateGradation(gradationExhibitionVO);
    }

    //    전시회 장소 이미지 수정
//    public void updateGradationImage(GradationExhibitionImgVO gradationExhibitionImgVO) {
//        exhibitionMapper.updateGradationImg(gradationExhibitionImgVO);
//    }

    //    전시회 장소 이미지 삭제
    public void deleteGradationImage(Long id) {
        exhibitionMapper.deleteGradationImg(id);
    }

}




