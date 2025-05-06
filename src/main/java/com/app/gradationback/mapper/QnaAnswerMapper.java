package com.app.gradationback.mapper;

import com.app.gradationback.domain.QnaAnswerVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QnaAnswerMapper {
    // 답변 등록
    public void insert(QnaAnswerVO qnaAnswerVO);

    // 답변 수정
    public void update(QnaAnswerVO qnaAnswerVO);

    // 답변 삭제
    public void delete(Long qnaId);
}
