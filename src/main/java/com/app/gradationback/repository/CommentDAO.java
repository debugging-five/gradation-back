package com.app.gradationback.repository;

import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.domain.CommentVO;
import com.app.gradationback.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentDAO {

    private final CommentMapper commentMapper;

//    댓글 작성
    public void save(CommentVO commentVO) {
        commentMapper.insert(commentVO);
    }

//    댓글 전체 조회
    public List<CommentVO> findAll() {
        return commentMapper.selectAll();
    }

//    댓글 단일 조회
    public Optional<CommentVO> findById(Long id) {
        return commentMapper.select(id);
    }

    public List<ArtPostDTO> findAllByUserId(Long userId) {
        return commentMapper.selectAllByUserId(userId);
    }

//    댓글 수정
    public void update(CommentVO commentVO) {
        commentMapper.update(commentVO);
    }

//    댓글 삭제
    public void delete(Long id) {
        commentMapper.delete(id);
    }

//    댓글 전체 삭제 (회원 탈퇴)
    public void deleteAllByUserId(Long userId) {
        commentMapper.deleteAllByUserId(userId);
    }

//    댓글 전체 삭제 (게시글 삭제)
    public void deleteAllByPostId(Long postId) {
        commentMapper.deleteAllByPostId(postId);
    }

    public List<CommentVO> findAllByPostId(Long postId) {
        return commentMapper.selectAllByPostId(postId);
    }
}
