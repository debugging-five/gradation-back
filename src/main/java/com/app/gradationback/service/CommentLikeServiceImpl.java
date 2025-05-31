package com.app.gradationback.service;

import com.app.gradationback.domain.CommentLikeVO;
import com.app.gradationback.repository.CommentDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CommentLikeServiceImpl implements CommentLikeService {

    private final CommentDAO commentDAO;

//    댓글 좋아요 등록
    @Override
    public void register(CommentLikeVO commentLikeVO) {
        if(!getCommentLiked(commentLikeVO)) {
            commentDAO.saveCommentLike(commentLikeVO);
        }

    }

//    댓글 좋아요 수
    @Override
    public int getCommentLikeCount(Long commentId) {
        return commentDAO.findCommentLikeCount(commentId);
    }

//    댓글 좋아요 여부
    @Override
    public boolean getCommentLiked(CommentLikeVO commentLikeVO) {
        if(commentDAO.findCommentLiked(commentLikeVO) == 1) {
            return true;
        }
        return false;
    }

//    댓글 좋아요 삭제
    @Override
    public void removeCommentLike(CommentLikeVO commentLikeVO) {
        commentDAO.deleteCommentLike(commentLikeVO);
    }

//    댓글 좋아요 전체 삭제
    @Override
    public void removeCommentLikeAll(Long commentId) {
        commentDAO.deleteAllCommentByCommentId(commentId);
    }
}
