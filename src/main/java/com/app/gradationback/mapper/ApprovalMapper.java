package com.app.gradationback.mapper;

import com.app.gradationback.domain.ArtDTO;
import com.app.gradationback.domain.UpcyclingDTO;
import com.app.gradationback.domain.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ApprovalMapper {
    // 업사이클링
    public List<UpcyclingDTO> selectAllUpcyclingPending();
    public Optional<UpcyclingDTO> selectUpcyclingPendingById(Long id);
    public void updateUpcyclingStatus(UpcyclingDTO upcyclingDTO);

    // 작품
    public List<ArtDTO> selectAllArtPending();
    public Optional<ArtDTO> selectArtPendingById(Long id);
    public void updateArtStatus(ArtDTO artDTO);

    // 작가 인증
    public List<UserVO> selectAllWriterPending();
    public Optional<UserVO> selectWriterPendingById(Long id);
    public void updateWriterStatus(UserVO userVO);

    // 대학 인증
    public List<UserVO> selectAllUniversityPending();
    public Optional<UserVO> selectUniversityPendingById(Long id);
    public void updateUniversityStatus(UserVO userVO);
}
