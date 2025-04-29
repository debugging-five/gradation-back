package com.app.gradationback.service;

import com.app.gradationback.domain.FaqDTO;
import com.app.gradationback.domain.FaqVO;

import java.util.List;
import java.util.Optional;

public interface FaqService {

//    자주 묻는 질문 등록
    public void register(FaqVO faqVO);

//    단일 조회
    public Optional<FaqVO> findByFaq(Long id);

//    전체 조회
    public List<FaqVO> getFaqList(FaqDTO faqDTO);

//    수정
    public void modify(FaqVO faqVO);

//    삭제
    public void remove(Long id);
}
