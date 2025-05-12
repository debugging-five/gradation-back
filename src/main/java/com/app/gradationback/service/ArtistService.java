package com.app.gradationback.service;

import com.app.gradationback.domain.ArtistDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ArtistService {

    public Optional<ArtistDTO> getMyArtistProfile(Map<String, Object> param);

    public List<ArtistDTO> getArtistList(Map<String, Object> param);

}
