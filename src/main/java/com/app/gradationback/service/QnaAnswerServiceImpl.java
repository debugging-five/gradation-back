package com.app.gradationback.service;

import com.app.gradationback.domain.QnaAnswerVO;
import com.app.gradationback.repository.QnaAnswerDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class QnaAnswerServiceImpl implements QnaAnswerService {

    private final QnaAnswerDAO qnaAnswerDAO;

    @Override
    public void register(QnaAnswerVO qnaAnswerVO) {
        qnaAnswerDAO.save(qnaAnswerVO);
    }

    @Override
    public void modify(QnaAnswerVO qnaAnswerVO) {
        qnaAnswerDAO.update(qnaAnswerVO);
    }

    @Override
    public void remove(Long qnaId) {
        qnaAnswerDAO.delete(qnaId);
    }
}
