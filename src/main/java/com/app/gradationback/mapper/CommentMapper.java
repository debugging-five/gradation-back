package com.app.gradationback.mapper;

import com.app.gradationback.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface CommentMapper {

//    댓글 작성
    public void insert(CommentVO commentVO);

//    댓글 전체 조회
    public List<CommentVO> selectAll();

//    댓글 단일 조회
    public Optional<CommentVO> select(Long id);

//    댓글 전체 조회 (userId로)
    public List<ArtPostDTO> selectAllByUserId(Long userId);

//    댓글 전체 조회 (postId로)
    public List<CommentDTO> selectAllByPostId(Map<String, Object> params);

//    댓글 수정
    public void update(CommentVO commentVO);

//    댓글 삭제
    public void delete(Long id);

//    댓글 전체 삭제 (회원 탈퇴)
    public void deleteAllByUserId(Long userId);

//    댓글 전체 삭제 (게시글 삭제)
    public void deleteAllByPostId(Long postId);

//    댓글 좋아요 등록
    public void insertCommentLike(CommentLikeVO commentLikeVO);

//    댓글 좋아요 수
    public int selectCommentLikeCount(Long commentId);

//    댓글 좋아요 여부
    public Integer selectCommentLiked(CommentLikeVO commentLikeVO);

//    댓글 좋아요 취소
    public void deleteCommentLike(CommentLikeVO commentLikeVO);

//    댓글 좋아요 삭제
    public void deleteAllCommentByCommentId(Long commentId);

}
