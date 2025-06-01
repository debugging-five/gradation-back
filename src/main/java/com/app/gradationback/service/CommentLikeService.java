package com.app.gradationback.service;

import com.app.gradationback.domain.CommentLikeVO;

public interface CommentLikeService {

//    댓글 좋아요 등록
    public void register(CommentLikeVO commentLikeVO);

//    댓글 좋아요 수
    public int getCommentLikeCount(Long commentId);

//    댓글 좋아요 여부
    public boolean getCommentLiked(CommentLikeVO commentLikeVO);

//    댓글 좋아요 삭제
    public void removeCommentLike(CommentLikeVO commentLikeVO);

//    댓글 좋아요 전체 삭제
    public void removeCommentLikeAll(Long commentId);
}
