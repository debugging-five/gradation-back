package com.app.gradationback.service;

import com.app.gradationback.domain.ArtDTO;
import com.app.gradationback.domain.UpcyclingDTO;
import com.app.gradationback.domain.UpcyclingVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UpcyclingService {
    // 전체 신청 내역 조회
    List<UpcyclingVO> getUpcyclingUserList();
    // ID 단건 조회
    Optional<UpcyclingVO> getByUpcyclingUser(Long id);
    // 신청 등록
    public void register(UpcyclingVO upcyclingVO, MultipartFile file);
    // 상태 변경
    public void modify(UpcyclingVO upcyclingVO);
    // 삭제
    public void remove(Long id);
}
