package com.app.gradationback.repository;

import com.app.gradationback.domain.ArtistDTO;
import com.app.gradationback.mapper.ArtistMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ArtistDAO {

    private final ArtistMapper artistMapper;

    public Optional<ArtistDTO> findMyArtistProfile(Map<String, Object> param) {
        return artistMapper.selectMyArtistProfile(param);
    }

    public List<ArtistDTO> findArtistList(Map<String, Object> param) {
        return artistMapper.selectArtistList(param);
    }
}
