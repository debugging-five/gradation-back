package com.app.gradationback.repository;

import com.app.gradationback.domain.FaqVO;
import com.app.gradationback.domain.QnaDTO;
import com.app.gradationback.domain.QnaVO;
import com.app.gradationback.mapper.QnaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class QnaDAO {

    private final QnaMapper qnaMapper;

    //    문의 등록
    public void save(QnaVO qnaVO){
        qnaMapper.insert(qnaVO);
    };

    //    목록
    public List<QnaDTO> findAll(String userEmail) {
        return qnaMapper.selectAll(userEmail);
    }

    //    단일게시글 조회
    public Optional<QnaDTO> findById(Long id){
        return qnaMapper.selectById(id);
    }

    //    게시글 수정
    public void update(QnaVO qnaVO){
        qnaMapper.update(qnaVO);
    }

    //    게시글 삭제
    public void delete(Long id){
        qnaMapper.delete(id);
    }

//    관리자용 전체 문의 내역 조회
    public List<QnaDTO> getAllQnaListForAdmin(){
        return qnaMapper.selectAllForAdmin();
    }
}
