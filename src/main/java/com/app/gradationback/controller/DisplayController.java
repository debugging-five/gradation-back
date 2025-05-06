package com.app.gradationback.controller;

import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.domain.ArtVO;
import com.app.gradationback.domain.CommentVO;
import com.app.gradationback.service.ArtPostService;
import com.app.gradationback.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/displays/api/*")
@RequiredArgsConstructor
@Slf4j
public class DisplayController {

    private final ArtPostService artPostService;
    private final CommentService commentService;

    //    전시 등록 (게시글 + 작품 + 이미지)
    @Operation(summary = "전시 등록", description = "전시를 등록할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "전시 등록 성공")
    @PostMapping("register")
    public ArtPostDTO register(@RequestBody ArtPostDTO artPostDTO) {
        log.info("{}", artPostDTO);
        artPostService.register(artPostDTO);
        Optional<ArtPostDTO> foundArtPost = artPostService.getArtPostById(artPostDTO.getArtPostId());
        if(foundArtPost.isPresent()) {
            return foundArtPost.get();
        }
        return new ArtPostDTO();
    }

//    게시글 전체 조회 + 댓글 전체 조회
    @Operation(summary = "전시 전체 조회", description = "게시글 + 작품 정보 + 이미지 + 댓글을 포함한 전시를 전체 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "전시 전체 조회 성공")
    @GetMapping("list")
    public List<Map<String, Object>> getAllPosts() {
        List<ArtPostDTO> posts = artPostService.getArtPostList();
//        게시글 + 댓글
        List<Map<String, Object>> postListWithComments = new ArrayList<>();

//        게시글 1개 + 댓글
        for (ArtPostDTO post : posts) {
            Map<String, Object> postWithComments = new HashMap<>();
            postWithComments.put("post", post);
            postWithComments.put("comments", commentService.getAllCommentByPostId(post.getId()));
            postListWithComments.add(postWithComments);
        }
        return postListWithComments;
    }

//    전시 단일 조회
    @Operation(summary = "전시 단일 조회", description = "게시글 + 작품 정보 + 이미지 + 댓글을 포함한 전시를 1개 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "전시 1개 조회 성공")
    @Parameter(
            name = "id",
            description = "게시글 번호",
            schema = @Schema(type = "number"), // 스키마 타입, 자바 타입X, swagger에서 정의되고 있는 타입
            in = ParameterIn.PATH, // 어디에서 받는지
            required = true
    )
    @GetMapping("/display/{postId}")
    public Map<String, Object> getPost(@PathVariable Long postId) {
        log.info("{}", postId);
        Optional<ArtPostDTO> foundArtPost = artPostService.getArtPostById(postId);
        Map<String, Object> response = new HashMap<>();

        if(foundArtPost.isPresent()) {
            ArtPostDTO post = foundArtPost.get();
            List<CommentVO> comments = commentService.getAllCommentByPostId(postId);
            response.put("comments", comments);
            response.put("post", post);
        }
        return response;
    }

//    전시 삭제 (작품 + 이미지 + 댓글 삭제)
    @Operation(summary = "전시 삭제", description = "전시를 삭제할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "전시 삭제 성공")
    @Parameter(
            name = "id",
            description = "게시글 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @DeleteMapping("/display/{postId}")
    public void deletePost(@PathVariable Long postId) {
        artPostService.removeById(postId);
    }
}







