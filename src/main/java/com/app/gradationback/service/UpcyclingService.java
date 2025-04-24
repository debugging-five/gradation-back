package com.app.gradationback.service;

import com.app.gradationback.domain.UpcyclingVO;

import java.util.List;
import java.util.Optional;

public interface UpcyclingService {
    // 전체 신청 내역 조회
    List<UpcyclingVO> getAll();
    // ID 단건 조회
    Optional<UpcyclingVO> getById(Long id);
    // 신청 등록
    void register(UpcyclingVO upcyclingVO);
    // 상태 변경
    void modify(UpcyclingVO upcyclingVO);
    // 삭제
    void remove(Long id);
}
