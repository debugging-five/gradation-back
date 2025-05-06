package com.app.gradationback.service;

import com.app.gradationback.domain.QnaAnswerVO;

public interface QnaAnswerService {
    // 답변 등록
    public void register(QnaAnswerVO qnaAnswerVO);
    // 답변 수정
    public void modify(QnaAnswerVO qnaAnswerVO);
    // 답변 삭제
    public void remove(Long qnaId);
}
