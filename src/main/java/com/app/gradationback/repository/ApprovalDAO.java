package com.app.gradationback.repository;

import com.app.gradationback.domain.ArtDTO;
import com.app.gradationback.domain.UniversityExhibitionDTO;
import com.app.gradationback.domain.UpcyclingDTO;
import com.app.gradationback.domain.UserVO;
import com.app.gradationback.mapper.ApprovalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ApprovalDAO {
    private final ApprovalMapper approvalMapper;

//    업사이클링 신청 대기 목록 조회
    public List<UpcyclingDTO> findAllUpcyclingPending() {
        return approvalMapper.selectAllUpcyclingPending();
    }

//    업사이클링 신청 완료 목록 조회
    public List<UpcyclingDTO> findAllUpcyclingCompleted() {
        return approvalMapper.selectAllUpcyclingCompleted();
    }

//    업사이클링 신청 대기 목록 중 단일 내용 조회
    public Optional<UpcyclingDTO> findUpcyclingPendingById(Long id) {
        return approvalMapper.selectUpcyclingPendingById(id);
    }

//    업사이클링 신청 상태 변경 (승인/반려)
    public void updateUpcyclingStatus(UpcyclingDTO upcyclingDTO) {
        approvalMapper.updateUpcyclingStatus(upcyclingDTO);
    }

//    작품 승인 대기 목록 조회
    public List<ArtDTO> findAllArtPending() {
        return approvalMapper.selectAllArtPending();
    }

//    작품 승인 완료 목록 조회
    public List<ArtDTO> findAllArtCompleted() {
        return approvalMapper.selectAllArtCompleted();
    }

//    작품 승인 대기 목록 중 단일 내용 조회
    public Optional<ArtDTO> findArtPendingById(Long id) {
        return approvalMapper.selectArtPendingById(id);
    }

//    작품 승인 상태 변경 (승인 / 반려)
    public void updateArtStatus(ArtDTO artDTO) {
        approvalMapper.updateArtStatus(artDTO);
    }

//    작가 승인 대기 목록 조회
    public List<UserVO> findAllWriterPending() {
        return approvalMapper.selectAllWriterPending();
    }

    public List<UserVO> findAllWriterCompleted() {
        return approvalMapper.selectAllWriterCompleted();
    }

//    작가 승인 대기 목록 중 단일 내용 조회
    public Optional<UserVO> findWriterPendingById(Long id) {
        return approvalMapper.selectWriterPendingById(id);
    }

//    작가 승인 상태 변경 (승인/반려)
    public void updateWriterStatus(UserVO userVO) {
        approvalMapper.updateWriterStatus(userVO);
    }

//    대학교 인증 대기 목록 조회
    public List<UserVO> findAllUniversityPending() {
        return approvalMapper.selectAllUniversityPending();
    }

//    대학교 인증 완료 목록 조회
    public List<UserVO> findAllUniversityCompleted() {return approvalMapper.selectAllUniversityCompleted();}

//    대학교 인증 대기 목록 중 단일 내용 조회
    public Optional<UserVO> findUniversityPendingById(Long id) { return approvalMapper.selectUniversityPendingById(id); }

//    대학교 인증 상태 변경 (승인.반려)
    public void updateUniversityStatus(UserVO userVO) { approvalMapper.updateUniversityStatus(userVO);
    }

    //    전시회 인증 대기 목록 조회
    public List<UniversityExhibitionDTO> findAllUniversityExhibitionPending() { return approvalMapper.selectAllUniversityExhibitionPending();
    }
    //    전시회 인증 완료 목록 조회
    public List<UniversityExhibitionDTO> findAllUniversityExhibitionCompleted() { return approvalMapper.selectAllUniversityExhibitionCompleted();
    }
    //    전시회 인증 대기 목록 중 단일 내용 조회
    public Optional<UniversityExhibitionDTO> findUniversityExhibitionPendingById(Long id) { return approvalMapper.selectUniversityExhibitionPendingById(id); }

    //    전시회 인증 상태 변경 (승인.반려)
    public void updateUniversityExhibitionStatus(UniversityExhibitionDTO universityExhibitionDTO) { approvalMapper.updateUniversityExhibitionStatus(universityExhibitionDTO);
    }

}
