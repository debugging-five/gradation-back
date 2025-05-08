package com.app.gradationback.service;

import com.app.gradationback.domain.GradationExhibitionImgVO;
import com.app.gradationback.domain.GradationExhibitionVO;
import com.app.gradationback.mapper.ExhibitionMapper;
import com.app.gradationback.repository.ExhibitionDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ExhibitionServiceImpl implements ExhibitionService {

    private final ExhibitionDAO exhibitionDAO;

    @Override
    public Optional<GradationExhibitionVO> getGradation() {
        return exhibitionDAO.findGradation();
    }

    @Override
    public List<GradationExhibitionImgVO> getGradationImgAll(Long gradationExhibitionId) {
        return exhibitionDAO.findGradationImgAll(gradationExhibitionId);
    }

    @Override
    public void registerGradation(GradationExhibitionVO gradationExhibitionVO) {
        exhibitionDAO.saveGradation(gradationExhibitionVO);
    }

    @Override
    public void registerGradationImage(GradationExhibitionImgVO gradationExhibitionImgVO) {
        exhibitionDAO.saveGradationImage(gradationExhibitionImgVO);
    }

    @Override
    public void editGradation(GradationExhibitionVO gradationExhibitionVO) {
        exhibitionDAO.updateGradation(gradationExhibitionVO);
    }

    @Override
    public void removeGradationImage(Long id) {
        exhibitionDAO.deleteGradationImage(id);
    }

    @Override
    public List<GradationExhibitionVO> getRecentGradations() {
        return exhibitionDAO.findRecentGradations();
    }
}
