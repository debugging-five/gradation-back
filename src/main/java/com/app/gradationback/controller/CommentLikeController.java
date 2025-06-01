package com.app.gradationback.controller;

import com.app.gradationback.domain.ArtLikeVO;
import com.app.gradationback.domain.CommentLikeVO;
import com.app.gradationback.service.ArtLikeService;
import com.app.gradationback.service.CommentLikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comment/likes/api/*")
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

//    댓글 좋아요 등록
    @Operation(summary = "댓글 좋아요 등록", description = "댓글 좋아요를 등록할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "댓글 좋아요 등록 성공")
    @PostMapping("registration")
    public ResponseEntity<Map<String, Object>> registerLike(@RequestBody CommentLikeVO commentLikeVO) {
        Map<String, Object> response = new HashMap<>();

        if (commentLikeService.getCommentLiked(commentLikeVO)) {
            response.put("message", "이미 좋아요 누름");
            response.put("status", commentLikeVO);
            response.put("isLiked", true);
            return ResponseEntity.ok(response);
        }
        try {
            commentLikeService.register(commentLikeVO);
        } catch (Exception e) {
            response.put("message", "좋아요 등록 실패");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        response.put("message", "좋아요 등록 성공");
        response.put("status", commentLikeVO);
        response.put("isLiked", false);
        return ResponseEntity.ok(response);
    }

//    댓글 좋아요 수
    @Operation(summary = "댓글 좋아요 수 조회", description = "댓글 좋아요 수를 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "댓글 좋아요 수 조회 성공")
    @GetMapping("/count/{commentId}")
    public int getLikeCount(@PathVariable Long commentId) {
        return commentLikeService.getCommentLikeCount(commentId);
    }

//    댓글 좋아요 여부
    @Operation(summary = "댓글 좋아요 여부 조회", description = "댓글 좋아요 여부를 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "작품 좋아요 여부 조회 성공")
    @PostMapping("/liked")
    public ResponseEntity<Map<String, Object>> isLiked(@RequestBody CommentLikeVO commentLikeVO) {
        Map<String, Object> response = new HashMap<>();
        boolean isLiked = commentLikeService.getCommentLiked(commentLikeVO);
        if (isLiked) {
            response.put("message", "좋아요 여부 true");
            response.put("isLiked", isLiked);
            return ResponseEntity.ok(response);
        }
        response.put("message", "좋아요 여부 false");
        response.put("isLiked", isLiked);
        return ResponseEntity.ok(response);
    }

//    댓글 좋아요 삭제
    @Operation(summary = "댓글 좋아요 삭제", description = "댓글 좋아요를 삭제할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "댓글 좋아요 삭제 성공")
    @DeleteMapping("delete")
    public ResponseEntity<Map<String, Object>> deleteLike(@RequestBody CommentLikeVO commentLikeVO) {
        Map<String, Object> response = new HashMap<>();
        try {
            commentLikeService.removeCommentLike(commentLikeVO);
        } catch (Exception e) {
            response.put("message", "좋아요 취소 실패");
            response.put("status", commentLikeVO);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        response.put("message", "좋아요 취소 성공");
        response.put("status", commentLikeVO);
        return ResponseEntity.ok(response);
    }
}
