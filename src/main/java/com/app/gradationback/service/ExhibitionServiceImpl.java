package com.app.gradationback.service;

import com.app.gradationback.domain.GradationExhibitionDTO;
import com.app.gradationback.domain.GradationExhibitionImgVO;
import com.app.gradationback.domain.GradationExhibitionVO;
import com.app.gradationback.repository.ExhibitionDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ExhibitionServiceImpl implements ExhibitionService {

    private final ExhibitionDAO exhibitionDAO;

    //    전시회 조회
    @Override
    public List<GradationExhibitionDTO> getGradation() {
        return exhibitionDAO.findGradationById();
    }

    //    전시회 등록
    @Override
    public void registerGradation(GradationExhibitionVO gradationExhibitionVO, List<GradationExhibitionImgVO> gradationExhibitionImgVO) {
        exhibitionDAO.saveGradation(gradationExhibitionVO);
        Long gradationId = gradationExhibitionVO.getId();
        for(GradationExhibitionImgVO image : gradationExhibitionImgVO) {
            image.setGradationExhibitionId(gradationId);
            exhibitionDAO.saveGradationImage(image);
        }
    }

    //    전시회 수정
    @Override
    public void editGradation(GradationExhibitionVO gradationExhibitionVO) {
        exhibitionDAO.updateGradation(gradationExhibitionVO);
//        for(GradationExhibitionImgVO image : gradationExhibitionImgVO) {
//            exhibitionDAO.updateGradationImage(image);
//        }
    }

    //    전시회 이미지 추가
    @Override
    public void registerGradationImage(GradationExhibitionImgVO gradationExhibitionImgVO) {
        exhibitionDAO.saveGradationImage(gradationExhibitionImgVO);
    }

    //    전시회 이미지 삭제
    @Override
    public void removeGradationImage(Long id) {
        exhibitionDAO.deleteGradationImage(id);
    }
}
