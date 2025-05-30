package com.app.gradationback.repository;

import com.app.gradationback.domain.*;
import com.app.gradationback.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
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

//   댓글 전체 조회 (userId로)
    public List<ArtPostDTO> findAllByUserId(Long userId) {
        return commentMapper.selectAllByUserId(userId);
    }

//    댓글 전체 조회 (postId로)
    public List<CommentDTO> findAllByPostId(Map<String, Object> params) {
        return commentMapper.selectAllByPostId(params);
    }

    public Integer findCountComment(Map<String, Object> params) {
        return commentMapper.selectCountComment(params);
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

//    댓글 좋아요 등록
    public void saveCommentLike(CommentLikeVO commentLikeVO) {
        commentMapper.insertCommentLike(commentLikeVO);
    }

//    댓글 좋아요 수
    public int findCommentLikeCount(Long commentId) {
        return commentMapper.selectCommentLikeCount(commentId);
    }

//    댓글 좋아요 여부
    public Integer findCommentLiked(CommentLikeVO commentLikeVO) {
        return commentMapper.selectCommentLiked(commentLikeVO);
    }

//    댓글 좋아요 삭제
    public void deleteCommentLike(CommentLikeVO commentLikeVO) {
        commentMapper.deleteCommentLike(commentLikeVO);
    }

//    댓글 좋아요 전체 삭제
    public void deleteAllCommentByCommentId(Long commentId) {
        commentMapper.deleteAllCommentByCommentId(commentId);
    }
}
