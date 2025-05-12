package com.app.gradationback.service;

import com.app.gradationback.domain.ArtistDTO;
import com.app.gradationback.repository.ArtistDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<ArtistDTO> getArtistList(Map<String, Object> param) {
        return artistDAO.findArtistList(param);
    }
}
