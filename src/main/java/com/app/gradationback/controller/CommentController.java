package com.app.gradationback.controller;

import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.domain.CommentVO;
import com.app.gradationback.domain.UserVO;
import com.app.gradationback.service.CommentService;
import com.app.gradationback.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comments/api/*")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;


    //    댓글 등록
    @Operation(summary = "댓글 등록", description = "댓글을 등록할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "댓글 등록 성공")
    @PostMapping("registration")
    public ResponseEntity<Map<String, Object>> write(@RequestBody CommentVO commentVO, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        Long userId = (Long) session.getAttribute("id");

        if (userId == null) {
            throw new RuntimeException("로그인이 필요합니다.");
        }

        // 유저 상태 확인
        UserVO user = userService.findUserByIdForWrite(userId)
                .orElseThrow(() -> new RuntimeException("회원 정보를 찾을 수 없습니다."));

        if (user.getUserBanOk() == 1) {
            throw new RuntimeException("댓글 작성이 제한된 사용자입니다.");
        } else if (user.getUserBanOk() == 2) {
            throw new RuntimeException("정지된 사용자입니다.");
        }

        // 댓글 등록
        commentVO.setUserId(userId);
        commentService.write(commentVO);

        Optional<CommentVO> foundComment = commentService.getComment(commentVO.getId());
        if (foundComment.isPresent()) {
//            commentVO.setId(foundReply.get().getId());
            response.put("message", "댓글 등록 성공했습니다.");
            response.put("reply", foundComment.get());
            return ResponseEntity.ok(response);
        }
        response.put("message", "댓글 등록 실패했습니다.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    //    댓글 전체 조회
    @Operation(summary = "댓글 전체 조회", description = "댓글을 전체 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "댓글 전체 조회 성공")
    @GetMapping("comments")
    public ResponseEntity<Map<String, Object>> getReplies() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<CommentVO> commentList = commentService.getCommentList();
            response.put("message", "댓글 전체 조회 성공했습니다.");
            response.put("commentList", commentList);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "댓글 전체 조회 실패했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    //    댓글 단일 조회
    @Operation(summary = "댓글 조회", description = "댓글을 1개 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "댓글 1개 조회 성공")
    @Parameter(
            name = "id",
            description = "댓글번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("comment/{id}")
    public ResponseEntity<Map<String, Object>> getReply(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<CommentVO> foundComment = commentService.getComment(id);
            if (foundComment.isPresent()) {
                String comment = foundComment.get().getCommentContent();
                response.put("message", "댓글 조회 성공했습니다.");
                response.put("comment", comment);
                return ResponseEntity.ok(response);
            }
            response.put("message", "해당 댓글이 존재하지 않습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("message", "서버 오류 발생");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("comment/user/{userId}")
    public ResponseEntity<Map<String, Object>> getComment(@PathVariable Long userId) {
        Map<String, Object> response = new HashMap<>();
        List<ArtPostDTO> commentList = commentService.getCommentListByUserId(userId);
        response.put("commentList", commentList);
        response.put("message", "댓글 조회 성공했습니다.");
        return ResponseEntity.ok(response);
    }

//    댓글 수정
    @Operation(summary = "댓글 수정", description = "댓글을 수정할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "댓글 수정 성공")
    @Parameter(
            name = "id",
            description = "댓글 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @PutMapping("comment/{id}")
    public ResponseEntity<Map<String, Object>> modify(@PathVariable Long id, @RequestBody CommentVO commentVO) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<CommentVO> foundReply = commentService.getComment(commentVO.getId());
            if (foundReply.isPresent()) {
                commentVO.setId(id);
                commentService.modifyComment(commentVO);

                Optional<CommentVO> updateComment = commentService.getComment(id);
                if(updateComment.isPresent()) {
                    response.put("message", "댓글 수정 성공했습니다.");
                    response.put("comment", commentVO.getCommentContent());
                    return ResponseEntity.ok(response);
                }
            }
            response.put("message", "해당 댓글이 존재하지 않습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//        return new CommentVO();
        } catch (Exception e) {
            response.put("message", "서버 오류");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

//    댓글 삭제
    @Operation(summary = "댓글 삭제", description = "댓글을 삭제할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "댓글 삭제 성공")
    @Parameter(
            name = "id",
            description = "댓글 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @DeleteMapping("comment/{id}")
    public ResponseEntity<Map<String, Object>> removeReply(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
//        commentService.removeComment(id);
        try {
            Optional<CommentVO> foundComment = commentService.getComment(id);
            if (foundComment.isPresent()) {
                commentService.removeComment(id);
                response.put("message", "댓글 삭제 성공했습니다.");
                return ResponseEntity.ok(response);
            }
            response.put("message", "해당 댓글이 존재하지 않습니다.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("message", "서버 오류");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }






}
