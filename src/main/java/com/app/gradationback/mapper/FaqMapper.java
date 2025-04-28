package com.app.gradationback.mapper;

import com.app.gradationback.domain.FaqDTO;
import com.app.gradationback.domain.FaqVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface FaqMapper {
//    자주 묻는 질문 등록
    public void insert (FaqVO faqVO);

//    자주 묻는 질문 리스트
    public List<FaqVO> selectAll();

//    자주 묻는 질문 단일조회
    public Optional<FaqVO> selectById(Long id);

//    질문 수정
    public void update(FaqVO faqVO);

//    질문 삭제
    public void delete(Long id);

}
