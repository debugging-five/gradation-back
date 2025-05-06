package com.app.gradationback.mapper;

import com.app.gradationback.domain.QnaDTO;
import com.app.gradationback.domain.QnaVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface QnaMapper {

//    문의 등록
    public void insert (QnaVO qnaVO);

//    전체조회
    public List<QnaDTO> selectAll(String userEmail);

//    문의 단일 조회
    public Optional<QnaDTO> selectById(Long id);

//    문의 수정
    public void update(QnaVO qnaVO);

//    문의 삭제
    public void delete(Long id);

//    관리자용 전체 문의 내역 조회
    public List<QnaDTO> selectAllForAdmin();

}
