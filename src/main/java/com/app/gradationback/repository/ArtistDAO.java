package com.app.gradationback.repository;

import com.app.gradationback.domain.ArtistDTO;
import com.app.gradationback.domain.ArtistDetailDTO;
import com.app.gradationback.domain.HistoryVO;
import com.app.gradationback.domain.UserVO;
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

    //    작가 메인페이지(내 프로필)
    public Optional<ArtistDTO> findMyArtistProfile(Map<String, Object> param) {
        return artistMapper.selectMyArtistProfile(param);
    }

    //    작가 메인페이지
    public List<ArtistDTO> findArtistList(Map<String, Object> params) {
        return artistMapper.selectArtistList(params);
    }

    //    작가 디테일(작가 정보 + history)
    public List<ArtistDetailDTO> findMyArtistById(Long userId) {
        return artistMapper.selectArtistById(userId);
    }

    //    작가 디테일(작품들 썸네일)
    public List<ArtistDetailDTO> findArtistArts(Long userId) {
        return artistMapper.selectArtistArts(userId);
    }

    //    작가 정보 수정
    public void updateArtist(UserVO userVO) {
        artistMapper.updateArtist(userVO);
    }

    //    작가 이력 추가
    public void saveUserHistory(HistoryVO historyVO) {
        artistMapper.insertUserHistory(historyVO);
    }

    //    작가 이력 삭제
    public void deleteUserHistory(Long id) {
        artistMapper.deleteUserHistory(id);
    }





}
