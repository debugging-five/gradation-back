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
    public List<ArtistDTO> getArtistList(Map<String, Object> params) {

        if (params.get("category").equals("sculpture")) {
            params.put("category", "조각");
        }else if(params.get("category").equals("craft")) {
            params.put("category", "공예");
        }else if(params.get("category").equals("architecture")) {
            params.put("category", "건축");
        }else if(params.get("category").equals("calligraphy")) {
            params.put("category", "서예");
        }else if(params.get("category").equals("painting")) {
            params.put("category", "회화");
        }else {
//            korean
            params.put("category", "한국화");
        }

        return artistDAO.findArtistList(params).stream().toList();
    }

    @Override
    public ArtistDetailDTO getArtistDetailById(Long userId) {
        List<ArtistDetailDTO> artistInfoList = artistDAO.findMyArtistById(userId);

        ArtistDetailDTO artistInfo = artistInfoList.get(0);

        List<HistoryVO> histories = new ArrayList<>();
        for(ArtistDetailDTO artistDetailDTO : artistInfoList) {
            if(artistDetailDTO.getHistoryDate() != null && artistDetailDTO.getHistoryContent() != null) {
                HistoryVO historyVO = new HistoryVO();
                historyVO.setId(artistDetailDTO.getHistoryId());
                historyVO.setHistoryDate(artistDetailDTO.getHistoryDate());
                historyVO.setHistoryContent(artistDetailDTO.getHistoryContent());
                histories.add(historyVO);
            }
        }

        artistInfo.setHistoryList(histories);
        return artistInfo;
    }

    @Override
    public List<ArtistDetailDTO> getArtistArtsList(Map<String, Object> params) {
        return artistDAO.findArtistArts(params);
    }

    @Override
    public Integer getCountArtistArts(Long userId) {
        return artistDAO.selectCountArtistArts(userId);
    }

    @Override
    public Integer getCountArtistList(Map<String, Object> params) {
        return artistDAO.findCountArtistList(params);
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

















