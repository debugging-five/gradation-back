package com.app.gradationback.controller;

import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.domain.CommentVO;
import com.app.gradationback.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comments/api/*")
public class CommentController {

    private final CommentService commentService;


//    댓글 등록
    @Operation(summary = "댓글 등록", description = "댓글을 등록할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "댓글 등록 성공")
    @PostMapping("registration")
    public ArtPostDTO write(@RequestBody CommentVO commentVO) {
        commentService.write(commentVO);

        Optional<CommentVO> foundReply = commentService.getComment(commentVO.getId());
        if (foundReply.isPresent()) {
            commentVO.setId(foundReply.get().getId());
        }
        return new ArtPostDTO();
    }

//    댓글 전체 조회
    @Operation(summary = "댓글 전체 조회", description = "댓글을 전체 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "댓글 전체 조회 성공")
    @GetMapping("comments")
    public List<CommentVO> getReplies() {
        return commentService.getCommentList();
    }

//    댓글 단일 조회
    @Operation(summary = "댓글 조회", description = "댓글을 1개 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "댓글 1개 조회 성공")
    @Parameter(
            name = "id",
            description = "댓글번호",
            schema = @Schema(type = "number"), // 스키마 타입, 자바 타입X, swagger에서 정의되고 있는 타입
            in = ParameterIn.PATH, // 어디에서 받는지
            required = true
    )
    @GetMapping("comment/{id}")
    public CommentVO getReply(@PathVariable Long id) {
        Optional<CommentVO> foundReply = commentService.getComment(id);
        if (foundReply.isPresent()) {
            return foundReply.get();
        }
        return new CommentVO();
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
    public CommentVO modify(@PathVariable Long id, @RequestBody CommentVO commentVO) {
        commentVO.setId(id);
        commentService.modifyComment(commentVO);
        Optional<CommentVO> foundReply = commentService.getComment(commentVO.getId());
        if (foundReply.isPresent()) {
            return foundReply.get();
        }
        return new CommentVO();
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
    public void removeReply(@PathVariable Long id) {
        commentService.removeComment(id);
    }





}
