package com.app.gradationback.mapper;

import com.app.gradationback.domain.ArtDTO;
import com.app.gradationback.domain.ArtVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ArtMapper {

//    작품 정보 등록
    public void insert (ArtVO artVO);

//    전체 작품 정보 조회
    public List<ArtVO> selectAll();

//    단일 작품 정보 조회
    public Optional<ArtVO> select(Long id);

//    작품 정보 수정
    public void update(ArtVO artVO);

//    작품 정보 삭제
    public void delete(Long id);

//    관리자용 승인 대기 목록
    public List<ArtDTO> selectAllPending();

//    관리자용 승인 대기 상세
    public Optional<ArtDTO> selectPendingById(Long id);

//    관리자용 승인 상태 변경
    public void updateStatus(ArtDTO artDTO);
}
