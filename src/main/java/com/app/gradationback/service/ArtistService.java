package com.app.gradationback.service;

import com.app.gradationback.domain.ArtistDTO;
import com.app.gradationback.domain.ArtistDetailDTO;
import com.app.gradationback.domain.HistoryVO;
import com.app.gradationback.domain.UserVO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ArtistService {

    public List<ArtistDTO> getArtistList(Map<String, Object> param);

    public ArtistDetailDTO getArtistDetailById(Long userId);

    public List<ArtistDetailDTO> getArtistArtsList(Map<String, Object> params);

    public Integer getCountArtistArts(Long userId);

    public Integer getCountArtistList(Map<String, Object> params);

    public void editArtist(UserVO userVO);

    public void registerUserHistory(HistoryVO historyVO);

    public void removeUserHistory(Long id);

}
