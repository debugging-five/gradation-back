package com.app.gradationback.service;

import com.app.gradationback.domain.GradationExhibitionImgVO;
import com.app.gradationback.domain.GradationExhibitionVO;
import com.app.gradationback.mapper.ExhibitionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ExhibitionServiceImpl implements ExhibitionService {

    private final ExhibitionMapper exhibitionMapper;

    @Override
    public Optional<GradationExhibitionVO> getGradation() {
        return exhibitionMapper.selectGradation();
    }

    @Override
    public List<GradationExhibitionImgVO> getGradationImgAll(Long gradationExhibitionId) {
        return exhibitionMapper.selectGradationImgAll(gradationExhibitionId);
    }

    @Override
    public void registerGradation(GradationExhibitionVO gradationExhibitionVO) {
        exhibitionMapper.insertGradation(gradationExhibitionVO);
    }

    @Override
    public void registerGradationImage(GradationExhibitionImgVO gradationExhibitionImgVO) {
        exhibitionMapper.insertGradationImg(gradationExhibitionImgVO);
    }

    @Override
    public void editGradation(GradationExhibitionVO gradationExhibitionVO) {
        exhibitionMapper.updateGradation(gradationExhibitionVO);
    }

    @Override
    public void removeGradationImage(Long id) {
        exhibitionMapper.deleteGradationImg(id);
    }
}
