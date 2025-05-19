package com.app.gradationback.service;

import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.domain.CommentVO;

import java.util.List;
import java.util.Optional;

public interface CommentService {

//    댓글 작성
    public void write(CommentVO commentVO);

//    댓글 전체 조회
    public List<CommentVO> getCommentList();

//    댓글 단일 조회
    public Optional<CommentVO> getComment(Long id);

//    댓글 전체 조회 (userId로)
    public List<ArtPostDTO> getCommentListByUserId(Long userId);

//    댓글 전체 조회 (postId로)
    public List<CommentVO> getAllCommentByPostId(Long postId);

//    댓글 삭제
    public void removeComment(Long id);

//    댓글 수정
    public void modifyComment(CommentVO commentVO);

//    댓글 전체 삭제 (회원 탈퇴)
    public void removeCommentByUserId(Long userId);

//    댓글 전체 삭제 (게시글 삭제)
    public void removeCommentByPostId(Long postId);

}
