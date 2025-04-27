package com.app.gradationback.repository;

import com.app.gradationback.domain.ArtVO;
import com.app.gradationback.domain.ReplyVO;
import com.app.gradationback.mapper.ArtMapper;
import com.app.gradationback.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReplyDAO {

    private final ReplyMapper replyMapper;

//    댓글 작성
    public void save(ReplyVO replyVO) {
        replyMapper.insert(replyVO);
    }

//    댓글 전체 조회
    public List<ReplyVO> findAll() {
        return replyMapper.selectAll();
    }

//    댓글 단일 조회
    public Optional<ReplyVO> findById(Long id) {
        return replyMapper.select(id);
    }

//    댓글 수정
    public void update(ReplyVO replyVO) {
        replyMapper.update(replyVO);
    }

//    댓글 삭제
    public void delete(Long id) {
        replyMapper.delete(id);
    }

//    댓글 전체 삭제 (회원 탈퇴)
    public void deleteAllByUserId(Long userId) {
        replyMapper.deleteAllByUserId(userId);
    }

//    댓글 전체 삭제 (게시글 삭제)
    public void deleteAllByPostId(Long postId) {
        replyMapper.deleteAllByPostId(postId);
    }

}
