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
    public void register(FaqVO faqVO) {
        faqDAO.save(faqVO);
    }

//    단일 조회
    @Override
    public Optional<FaqVO> findByFaq(Long id) {
        return faqDAO.findByFaq(id);
    }

//    목록
    @Override
    public List<FaqVO> getFaqList(FaqDTO faqDTO) {
        return faqDAO.getFaqList(faqDTO);
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
