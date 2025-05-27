package com.app.gradationback.mapper;

import com.app.gradationback.domain.ArtDTO;
import com.app.gradationback.domain.UniversityExhibitionDTO;
import com.app.gradationback.domain.UpcyclingDTO;
import com.app.gradationback.domain.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ApprovalMapper {
    // 업사이클링
    public List<UpcyclingDTO> selectAllUpcyclingPending();
    public List<UpcyclingDTO> selectAllUpcyclingCompleted();
    public Optional<UpcyclingDTO> selectUpcyclingPendingById(Long id);
    public Optional<UpcyclingDTO> selectUpcyclingCompletedById(Long id);
    public void updateUpcyclingStatus(UpcyclingDTO upcyclingDTO);

    // 작품
    public List<ArtDTO> selectAllArtPending();
    public List<ArtDTO> selectAllArtCompleted();
    public Optional<ArtDTO> selectArtPendingById(Long id);
    public Optional<ArtDTO> selectArtCompletedById(Long id);
    public void updateArtStatus(ArtDTO artDTO);

    // 작가 인증
    public List<UserVO> selectAllWriterPending();
    public List<UserVO> selectAllWriterCompleted();
    public Optional<UserVO> selectWriterPendingById(Long id);
    public Optional<UserVO> selectWriterCompletedById(Long id);
    public void updateWriterStatus(UserVO userVO);

    // 대학 인증
    public List<UserVO> selectAllUniversityPending();
    public List<UserVO> selectAllUniversityCompleted();
    public Optional<UserVO> selectUniversityPendingById(Long id);
    public Optional<UserVO> selectUniversityCompletedById(Long id);
    public void updateUniversityStatus(UserVO userVO);

    // 대학 전시회 인증
    public List<UniversityExhibitionDTO> selectAllUniversityExhibitionPending();
    public List<UniversityExhibitionDTO> selectAllUniversityExhibitionCompleted();
    public Optional<UniversityExhibitionDTO> selectUniversityExhibitionPendingById(Long id);
    public Optional<UniversityExhibitionDTO> selectUniversityExhibitionCompletedById(Long id);
    public void updateUniversityExhibitionStatus(UniversityExhibitionDTO universityExhibitionDTO);
}
