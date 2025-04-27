package com.app.gradationback.service;

import com.app.gradationback.domain.FaqVO;

import java.util.List;
import java.util.Optional;

public interface FaqService {

//    자주 묻는 질문 등록
    public void registraction(FaqVO faqVO);

//    단일 조회
    public Optional<FaqVO> getFaq(Long id);

//    전체 조회
    public List<FaqVO> getFaqList();

//    수정
    public void modify(FaqVO faqVO);

//    삭제
    public void remove(Long id);
}
