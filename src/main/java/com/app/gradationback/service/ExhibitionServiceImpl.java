package com.app.gradationback.service;

import com.app.gradationback.domain.*;
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

    @Override
    public List<DisplayDTO> getTopLikedArts() {
        return exhibitionDAO.findTopLikedArts();
    }

    @Override
    public void registerUniversity(UniversityExhibitionDTO universityExhibitionDTO) {
//        대학교 중복 확인(로고)
        Optional<UniversityVO> university = exhibitionDAO.findUniversityByName(universityExhibitionDTO.getUniversityName());

        if(university.isPresent()) {
            UniversityVO universityVO = university.get();
            universityExhibitionDTO.setId(universityVO.getId());
            universityExhibitionDTO.setUniversityLogoImgName(universityVO.getUniversityLogoImgName());
            universityExhibitionDTO.setUniversityLogoImgPath(universityVO.getUniversityLogoImgPath());
        } else {
            universityExhibitionDTO.setUniversityLogoImgName("default-logo.png");
            universityExhibitionDTO.setUniversityLogoImgPath("assets/images/university/logo");
            exhibitionDAO.saveUniversity(universityExhibitionDTO);
        }
//        학과 저장
        exhibitionDAO.saveMajor(universityExhibitionDTO);
//        전시회 저장
        exhibitionDAO.saveUniversityExhibition(universityExhibitionDTO);
//        이미지 저장
        if(universityExhibitionDTO.getUniversityExhibitionImgName() != null && universityExhibitionDTO.getUniversityExhibitionImgPath() != null) {
            universityExhibitionDTO.setUniversityExhibitionId(universityExhibitionDTO.getId());
            exhibitionDAO.saveUniversityExhibitionImg(universityExhibitionDTO);
        }
    }

//    대학 전시회 정보
    @Override
    public List<UniversityExhibitionDTO> getUniversity() {
        return exhibitionDAO.findUniversity();
    }

    @Override
    public List<UniversityExhibitionImgVO> getUniversityImgAll(Long universityExhibitionId) {
        return exhibitionDAO.findUniversityImgAll(universityExhibitionId);
    }

//    대학 전시회 사진



}




