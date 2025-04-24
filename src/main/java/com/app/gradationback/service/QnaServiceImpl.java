package com.app.gradationback.service;

import com.app.gradationback.domain.QnaDTO;
import com.app.gradationback.domain.QnaVO;
import com.app.gradationback.repository.QnaDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService {

    private final QnaDAO qnaDAO;

//    등록
    @Override
    public void registraction(QnaVO qnaVO) {
        qnaDAO.registraction(qnaVO);
    }

//    단일조회
    @Override
    public Optional<QnaDTO> getQna(Long id) {
        return qnaDAO.findById(id);
    }

//    목록
    @Override
    public List<QnaDTO> getQnaList(String userEmail) {
        return qnaDAO.findAll(userEmail);
    }

//    수정
    @Override
    public void modify(QnaVO qnaVO) {
        qnaDAO.update(qnaVO);
    }

//    삭제
    @Override
    public void remove(Long id) {
        qnaDAO.delete(id);
    }
}
