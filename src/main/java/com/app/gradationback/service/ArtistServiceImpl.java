package com.app.gradationback.service;

import com.app.gradationback.domain.*;
import com.app.gradationback.repository.ArtistDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ArtistServiceImpl implements ArtistService {

    private final ArtistDAO artistDAO;

    @Override
    public Optional<ArtistDTO> getMyArtistProfile(Map<String, Object> param) {
        return artistDAO.findMyArtistProfile(param);
    }

    @Override
    public List<ArtistDTO> getArtistList(Map<String, Object> params) {
        return artistDAO.findArtistList(params);
    }

    @Override
    public ArtistDetailDTO getArtistDetailById(Long userId) {
        List<ArtistDetailDTO> artistInfoList = artistDAO.findMyArtistById(userId);
        List<ArtistDetailDTO> artistArtList = artistDAO.findArtistArt(userId);

        ArtistDetailDTO artistInfo = artistInfoList.get(0);

        List<HistoryVO> histories = new ArrayList<>();
        for(ArtistDetailDTO artistDetailDTO : artistInfoList) {
            if(artistDetailDTO.getHistoryDate() != null && artistDetailDTO.getHistoryContent() != null) {
                HistoryVO historyVO = new HistoryVO();
                historyVO.setHistoryDate(artistDetailDTO.getHistoryDate());
                historyVO.setHistoryContent(artistDetailDTO.getHistoryContent());
                histories.add(historyVO);
            }
        }

        List<ArtImgVO> artImgList = new ArrayList<>();
        for(ArtistDetailDTO artistDetailDTO : artistArtList) {
            ArtImgVO artImgVO = new ArtImgVO();
            artImgVO.setArtId(artistDetailDTO.getArtId());
            artImgVO.setArtImgName(artistDetailDTO.getArtImgName());
            artImgVO.setArtImgPath(artistDetailDTO.getArtImgPath());
            artImgList.add(artImgVO);
        }

        artistInfo.setHistoryList(histories);
        artistInfo.setArtImgList(artImgList);

        return artistInfo;
    }

    @Override
    public void editArtist(UserVO userVO) {
        artistDAO.updateArtist(userVO);
    }

    @Override
    public void registerUserHistory(HistoryVO historyVO) {
        artistDAO.saveUserHistory(historyVO);
    }

    @Override
    public void removeUserHistory(Long id) {
        artistDAO.deleteUserHistory(id);
    }

}

















