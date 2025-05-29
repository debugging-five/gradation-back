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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("registration")
    public ArtPostDTO register(@RequestBody ArtPostDTO artPostDTO) {
//        log.info("{}", artPostDTO);
        Long postId = artPostService.register(artPostDTO);
        return artPostService.getArtPostById(postId).orElseThrow(() -> new RuntimeException("조회 실패"));
    }

//    게시글 전체 조회 + 댓글 전체 조회
    @Operation(summary = "전시 전체 조회", description = "게시글 + 작품 정보 + 이미지 + 댓글을 포함한 전시를 전체 조회할 수 있는 API")
    @Parameters({
            @Parameter(name = "order", description = "정렬기준", example = "popular"),
            @Parameter(name = "cursor", description = "페이지", example = "1"),
            @Parameter(name = "category", description = "분류", example = "건축, 회화, 한국화, 조각, 서예, 공예"),
            @Parameter(name = "keyword", description = "검색", example = "작가명, 작품명")
    })
    @ApiResponse(responseCode = "200", description = "전시 전체 조회 성공")
    @PostMapping("list")
    public ResponseEntity<Map<String, Object>> getAllPosts(@RequestBody HashMap<String, Object> params) {
        Map<String, Object> response = new HashMap<>();
        List<ArtPostDTO> postList = artPostService.getArtListByCategoryAndDropdown(params);
        if(postList.isEmpty()) {
            response.put("postList", postList);
            response.put("message", "작품 리스트 조회 성공했습니다.");
            response.put("contents", 0);
            response.put("params", params);
            return ResponseEntity.ok(response);
        }
        response.put("postList", postList);
        response.put("message", "작품 리스트 조회 성공했습니다.");
        response.put("params", params);
        response.put("cursor", params.get("cursor"));
        response.put("contents", artPostService.getCountArtList(params));
        return ResponseEntity.ok(response);
//        response.put("posts", artPostService.getArtListByCategoryAndDropdown(params));
//        return ResponseEntity.ok(response);
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
    @GetMapping("/read/{id}")
    public ResponseEntity<Map<String, Object>> getPost(@PathVariable Long id) {
//        log.info("id : {}", id);
//        log.info("postId : {}", postId);
        Optional<ArtPostDTO> foundArtPost = artPostService.getArtPostById(id);
        Map<String, Object> response = new HashMap<>();

        if(foundArtPost.isPresent()) {
            response.put("post", foundArtPost.get());
            response.put("message", "게시글 조회 성공했습니다.");
            return ResponseEntity.ok(response);
        }
        response.put("message", "게시글 조회 실패하였습니다.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


//    메인 등록순 상위 50개 작품 조회
    @Operation(summary = "등록순으로 상위 50개 작품 목록 조회", description = "등록순으로 상위 50개 작품 목록을 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "등록순으로 상위 50개 작품 목록 조회 성공")
    @GetMapping("/list/main")
    public ResponseEntity<Map<String, Object>> getArtListForMain() {
        Map<String, Object> response = new HashMap<>();
        List<ArtPostDTO> artListForMain = artPostService.getArtListForMain();
        response.put("artListForMain", artListForMain);
        response.put("message", "작품 조회 성공했습니다.");
        return ResponseEntity.ok(response);
    }

//    내 작품 리스트
    @Operation(summary = "내 작품 목록 조회", description = "내 작품 목록을 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "내 작품 목록 조회 성공")
    @GetMapping("/list/my")
    public ResponseEntity<Map<String, Object>> getMyArtList(@RequestParam Long userId) {
        Map<String, Object> response = new HashMap<>();
        List<ArtPostDTO> myArtList = artPostService.getMyArtList(userId);
        response.put("myArtList", myArtList);
        response.put("message", "내 작품 조회 성공했습니다.");
        return ResponseEntity.ok(response);
    }

//    내 작품 좋아요
    @Operation(summary = "내 작품 좋아요 조회", description = "좋아요 누른 작품 목록을 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "좋아요 누른 작품 목록 조회 성공")
    @GetMapping("/list/my/liked")
    public ResponseEntity<Map<String, Object>> getLikedArtList(@RequestParam Long userId) {
        Map<String, Object> response = new HashMap<>();
        List<ArtPostDTO> artLikeList = artPostService.getLikedArtList(userId);
        response.put("likedArtList", artLikeList);
        response.put("message", "내 작품 좋아요 목록 조회 성공했습니다.");
        return ResponseEntity.ok(response);
    }

//    경매 가능 작품 조회 (좋아요 50개 이상)
    @Operation(summary = "경매 가능 작품 조회", description = "경매 가능한 작품 목록을 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "경매 가능 작품 목록 조회 성공")
    @GetMapping("/list/auction")
    public ResponseEntity<Map<String, Object>> getArtListForAuction(@RequestParam Long userId) {
        Map<String, Object> response = new HashMap<>();
        List<ArtPostDTO> artListForAuction = artPostService.getArtListForAuction(userId);
        response.put("artListForAuction", artListForAuction);
        response.put("message", "경매 가능 작품 조회 성공했습니다.");
        return ResponseEntity.ok(response);
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
    @DeleteMapping("/delete/{postId}")
    public void deletePost(@PathVariable Long postId) {
        artPostService.removeById(postId);
    }

}

