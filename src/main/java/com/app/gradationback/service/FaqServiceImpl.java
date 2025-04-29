package com.app.gradationback.service;

import com.app.gradationback.domain.FaqDTO;
import com.app.gradationback.domain.FaqVO;
import com.app.gradationback.repository.FaqDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService {

    private final FaqDAO faqDAO;

//    등록
    @Override
    public void registraction(FaqVO faqVO) {
        faqDAO.registraction(faqVO);
    }

//    단일 조회
    @Override
    public Optional<FaqVO> getFaq(Long id) {
        return faqDAO.findById(id);
    }

//    목록
    @Override
    public List<FaqVO> getFaqList() {
        return faqDAO.findAll();
    }

//    수정
    @Override
    public void modify(FaqVO faqVO) {
        faqDAO.update(faqVO);
    }

//    삭제
    @Override
    public void remove(Long id) {
        faqDAO.delete(id);
    }
}
