package com.app.gradationback.repository;

import com.app.gradationback.domain.FaqDTO;
import com.app.gradationback.domain.FaqVO;
import com.app.gradationback.mapper.FaqMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FaqDAO {
    private final FaqMapper faqMapper;

//    자주 묻는 질문 등록
    public void save(FaqVO faqVO){
        faqMapper.insert(faqVO);
};

//    목록
    public List<FaqVO> getFaqList(FaqDTO faqDTO) {
        return faqMapper.selectAll();
}

//    단일게시글 조회
    public Optional<FaqVO> findByFaq(Long id){
        return faqMapper.selectById(id);
}

//    게시글 수정
    public void update(FaqVO faqVO){
        faqMapper.update(faqVO);
}

//    게시글 삭제
    public void delete(Long id){
        faqMapper.delete(id);
}

}
