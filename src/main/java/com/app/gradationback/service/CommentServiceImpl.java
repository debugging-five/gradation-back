package com.app.gradationback.service;

import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.domain.CommentDTO;
import com.app.gradationback.domain.CommentVO;
import com.app.gradationback.repository.CommentDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class CommentServiceImpl implements CommentService {

    private final CommentDAO commentDAO;

//    댓글 등록
    @Override
    public void write(CommentVO commentVO) {
        commentDAO.save(commentVO);
    }

//    댓글 전체 조회
    @Override
    public List<CommentVO> getCommentList() {
        return commentDAO.findAll();
    }

//    댓글 단일 조회
    @Override
    public Optional<CommentVO> getComment(Long id) {
        return commentDAO.findById(id);
    }

//    댓글 전체 조회 (userId로)
    @Override
    public List<ArtPostDTO> getCommentListByUserId(Long userId) {
        return commentDAO.findAllByUserId(userId);
    }

//    댓글 전체 조회 (postId로)
    @Override
    public List<CommentDTO> getAllCommentByPostId(Map<String, Object> params) {
        return commentDAO.findAllByPostId(params);
    }

    @Override
    public Integer getCountComment(Map<String, Object> params) {
        return commentDAO.findCountComment(params);
    }

    //    댓글 수정
    @Override
    public void modifyComment(CommentVO commentVO) {
        commentDAO.update(commentVO);
    }

//    댓글 삭제
    @Override
    public void removeComment(Long id) {
        commentDAO.delete(id);
    }

//    댓글 전체 삭제 (회원 탈퇴)
    @Override
    public void removeCommentByUserId(Long userId) {
        commentDAO.deleteAllByUserId(userId);
    }

//    댓글 전체 삭제 (게시글 삭제)
    @Override
    public void removeCommentByPostId(Long postId) {
        commentDAO.deleteAllByPostId(postId);
    }

}
