//package com.app.gradationback.mapper.mina;
//
//import com.app.gradationback.domain.CommentVO;
//import com.app.gradationback.mapper.ArtPostMapper;
//import com.app.gradationback.mapper.CommentMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//@SpringBootTest
//@Slf4j
//public class CommentMapperTests {
//
//    @Autowired
//    private CommentMapper commentMapper;
//
//    @Autowired
//    private ArtPostMapper artPostMapper;
//
////    댓글 등록
//    @Test
//    public void insertReplyTest() {
//        CommentVO commentVO = new CommentVO();
//        commentVO.setArtPostId(3L);
//        commentVO.setUserId(64L);
//        commentVO.setCommentContent("유저3의 댓글2");
//        commentVO.setCommentDate(new Timestamp(System.currentTimeMillis()));
//        commentMapper.insert(commentVO);
//        log.info("{}", commentVO);
//    }
//
////    댓글 전체 조회
//    @Test
//    public void selectAllReplyTest() {
//        List<CommentVO> commentVOList = commentMapper.selectAll();
//        for (CommentVO commentVO : commentVOList) {
//            log.info("{}", commentVO);
//        }
//    }
//
////    댓글 단일 조회
//    @Test
//    public void selectReplyTest() {
//        CommentVO commentVO = new CommentVO();
//        commentVO.setId(23L);
//        commentMapper.select(commentVO.getId()).map(CommentVO::toString).ifPresent(log::info);
//    }
//
////    댓글 수정
//    @Test
//    public void updateReplyTest() {
//        CommentVO commentVO = new CommentVO();
//        commentVO.setId(23L);
//        commentVO.setCommentContent("수정된 댓글댓글");
//        commentVO.setCommentDate(new Timestamp(System.currentTimeMillis()));
//        commentMapper.update(commentVO);
//        log.info("{}", commentVO);
//    }
//
////    댓글 삭제
//    @Test
//    public void deleteReplyTest() {
//        commentMapper.delete(23L);
//    }
//
////    댓글 전체 삭제 (회원 탈퇴)
//    @Test
//    public void deleteAllReplyByUserIdTest() {
//        commentMapper.deleteAllByUserId(58L);
//    }
//
////    댓글 전체 삭제 (게시글 삭제)
//    @Test
//    public void deleteAllReplyByPostIdTest() {
//        commentMapper.deleteAllByPostId(5L);
//        artPostMapper.delete(5L);
//    }
//}
