package com.app.gradationback.mapper;

import com.app.gradationback.domain.ArtDTO;
import com.app.gradationback.domain.UpcyclingDTO;
import com.app.gradationback.domain.UpcyclingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UpcyclingMapper {
    //신청 내역 조회
    public List<UpcyclingDTO> selectAll(Long userId);
    //ID 단건 조회
    public Optional<UpcyclingVO> select(Long id);
    //업사이클링 신청 등록
    public void insert(UpcyclingVO upcyclingVO);
    //상태 변경
    public void update(UpcyclingVO upcyclingVO);
    //삭제
    public void delete(Long id);
    //    관리자용 승인 대기 목록 조회
    public List<UpcyclingDTO> selectAllUpcyclingPending();

    //    관리자용 승인 대기 상세 조회
    public Optional<UpcyclingDTO> selectUpcyclingPendingById(Long id);

    //    관리자용 승인 상태 변경
    public void updateUpcyclingStatus(UpcyclingDTO upcyclingDTO);
}
