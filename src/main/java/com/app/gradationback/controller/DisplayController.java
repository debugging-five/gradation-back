package com.app.gradationback.controller;

import com.app.gradationback.domain.*;
import com.app.gradationback.service.ArtImgService;
import com.app.gradationback.service.ArtLikeService;
import com.app.gradationback.service.ArtPostService;
import com.app.gradationback.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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
    private final ArtImgService artImgService;
    private final ArtLikeService artLikeService;

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
    @Parameters({
            @Parameter(name = "order", description = "정렬기준", example = "popular"),
            @Parameter(name = "cursor", description = "페이지", example = "1"),
            @Parameter(name = "direction", description = "오름차순", example = "asc"),
            @Parameter(name = "category", description = "분류", example = "건축, 회화, 한국화, 조각, 서예, 공예"),
            @Parameter(name = "keyword", description = "검색", example = "작가명, 작품명")
    })
    @ApiResponse(responseCode = "200", description = "전시 전체 조회 성공")
    @GetMapping("list")
    public List<Map<String, Object>> getAllPosts(@RequestParam HashMap<String, Object> params) {
        List<ArtPostDTO> posts = artPostService.getArtListByCategoryAndDropdown(params);
//        게시글 + 댓글
        List<Map<String, Object>> postListWithComments = new ArrayList<>();

//        게시글 1개 + 댓글
        for (ArtPostDTO post : posts) {
            Map<String, Object> postDetail = new HashMap<>();
            postDetail.put("post", post);
            postDetail.put("comments", commentService.getAllCommentByPostId(post.getId()));
            postDetail.put("images", artImgService.getArtImgListByArtId(post.getArtId()));
            postListWithComments.add(postDetail);
        }
        return postListWithComments;
    }

//    전시 단일 조회
    @Operation(summary = "전시 단일 조회", description = "게시글 + 작품 정보 + 이미지 + 댓글을 포함한 전시를 1개 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "전시 1개 조회 성공")
    @Parameter(
            name = "id",
            description = "게시글 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("/display/{postId}")
    public Map<String, Object> getPost(@PathVariable Long postId) {
        log.info("{}", postId);
        Optional<ArtPostDTO> foundArtPost = artPostService.getArtPostById(postId);
        Map<String, Object> response = new HashMap<>();

        if(foundArtPost.isPresent()) {
            ArtPostDTO post = foundArtPost.get();
            Long artId = post.getArtId();
            List<CommentVO> comments = commentService.getAllCommentByPostId(postId);
            List<ArtImgVO> images = artImgService.getArtImgListByArtId(artId);
            response.put("post", post);
            response.put("comments", comments);
            response.put("images", images);
            response.put("likeCount", artLikeService.getLikeCount(artId));
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

//    좋아요 등록
    @Operation(summary = "좋아요 등록", description = "좋아요를 등록할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "좋아요 등록 성공")
    @PostMapping("/like")
    public void registerLike(@RequestBody ArtLikeVO artLikeVO) {
        artLikeService.register(artLikeVO);
    }

//    좋아요 수
    @Operation(summary = "좋아요 수 조회", description = "좋아요 수를 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "좋아요 수 조회 성공")
    @GetMapping("/like/count/{artId}")
    public int getLikeCount(Long artId) {
        return artLikeService.getLikeCount(artId);
    }

//    좋아요 삭제
    @Operation(summary = "좋아요 삭제", description = "좋아요를 삭제할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "좋아요 삭제 성공")
    @DeleteMapping("/like")
    public void deleteLike(@RequestBody ArtLikeVO artLikeVO) {
        artLikeService.remove(artLikeVO);
    }

}







