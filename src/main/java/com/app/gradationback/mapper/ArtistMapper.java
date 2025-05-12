package com.app.gradationback.mapper;

import com.app.gradationback.domain.ArtistDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface ArtistMapper {

//    작가 메인페이지(내 프로필)
    public Optional<ArtistDTO> selectMyArtistProfile(Map<String, Object> param);

//    작가 메인페이지
    public List<ArtistDTO> selectArtistList(Map<String, Object> param);

}
