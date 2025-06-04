package com.app.gradationback.mapper;

import com.app.gradationback.domain.ArtistDTO;
import com.app.gradationback.domain.ArtistDetailDTO;
import com.app.gradationback.domain.HistoryVO;
import com.app.gradationback.domain.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface ArtistMapper {

//    작가 메인페이지
    public List<ArtistDTO> selectArtistList(Map<String, Object> params);

//    작가 디테일(작가정보 + history)
    public List<ArtistDetailDTO> selectArtistById(Long userId);

//    작가 디테일(작품들 썸네일)
    public List<ArtistDetailDTO> selectArtistArts(Map<String, Object> params);

    public Integer selectCountArtistArts(Long userId);

    public Integer selectCountArtistList(Map<String, Object> params);

//    작가 정보 수정
    public void updateArtist(UserVO userVO);

//    작가 이력 추가
    public void insertUserHistory(HistoryVO historyVO);

//    작가 이력 삭제
    public void deleteUserHistory(Long id);


}
