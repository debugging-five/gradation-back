package com.app.gradationback.repository;

import com.app.gradationback.domain.QnaAnswerVO;
import com.app.gradationback.mapper.QnaAnswerMapper;
import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QnaAnswerDAO {

    private final QnaAnswerMapper qnaAnswerMapper;

    // 답변 등록
    public void save(QnaAnswerVO qnaAnswerVO) {qnaAnswerMapper.insert(qnaAnswerVO);}

    // 답변 수정
    public void update(QnaAnswerVO qnaAnswerVO) {qnaAnswerMapper.update(qnaAnswerVO);}

    // 답변 삭제
    public void delete(Long qnaId) {qnaAnswerMapper.delete(qnaId);}


}
