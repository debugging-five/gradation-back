package com.app.gradationback.mapper;

import com.app.gradationback.domain.ArtVO;
import com.app.gradationback.domain.ReplyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ReplyMapper {

//    댓글 작성
    public void insert(ReplyVO replyVO);

//    댓글 전체 조회
    public List<ReplyVO> selectAll();

//    댓글 단일 조회
    public Optional<ReplyVO> select(Long id);

//    댓글 수정
    public void update(ReplyVO replyVO);

//    댓글 삭제
    public void delete(Long id);

//    댓글 전체 삭제 (회원 탈퇴)
    public void deleteAllByUserId(Long userId);

//    댓글 전체 삭제 (게시물 삭제)
    public void deleteAllByPostId(Long postId);

}
