package com.app.gradationback.service;

import com.app.gradationback.domain.ArtDTO;
import com.app.gradationback.domain.UpcyclingDTO;
import com.app.gradationback.domain.UpcyclingVO;

import java.util.List;
import java.util.Optional;

public interface UpcyclingService {
    // 전체 신청 내역 조회
    List<UpcyclingVO> getUpcyclingUserList();
    // ID 단건 조회
    Optional<UpcyclingVO> getByUpcyclingUser(Long id);
    // 신청 등록
    public void register(UpcyclingVO upcyclingVO);
    // 상태 변경
    public void modify(UpcyclingVO upcyclingVO);
    // 삭제
    public void remove(Long id);

    //    관리자용 승인 대기 목록
    public List<UpcyclingDTO> getAllUpcyclingPending();

    //    관리자용 상세
    public Optional<UpcyclingDTO> getUpcyclingPendingById(Long id);

    //    관리자 승인/반려 처리
    public void updateUpcyclingStatus(UpcyclingDTO upcyclingDTO);

}
