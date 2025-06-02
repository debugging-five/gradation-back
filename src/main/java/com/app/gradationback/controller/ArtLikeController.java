package com.app.gradationback.controller;

import com.app.gradationback.domain.ArtLikeVO;
import com.app.gradationback.service.ArtLikeService;
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
@RequestMapping("/art/likes/api/*")
public class ArtLikeController {

    private final ArtLikeService artLikeService;

//    좋아요 등록
    @Operation(summary = "작품 좋아요 등록", description = "작품 좋아요를 등록할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "작품 좋아요 등록 성공")
    @PostMapping("registration")
    public ResponseEntity<Map<String, Object>> registerLike(@RequestBody ArtLikeVO artLikeVO) {
        Map<String, Object> response = new HashMap<>();

        if (artLikeService.getLiked(artLikeVO)) {
            response.put("message", "이미 좋아요 누름");
            response.put("status", artLikeVO);
            response.put("isLiked", true);
            return ResponseEntity.ok(response);
        }
        try {
            artLikeService.register(artLikeVO);
        } catch (Exception e) {
            response.put("message", "좋아요 등록 실패");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        response.put("message", "좋아요 등록 성공");
        response.put("status", artLikeVO);
        response.put("isLiked", false);
        return ResponseEntity.ok(response);
    }

//    좋아요 수
    @Operation(summary = "작품 좋아요 수 조회", description = "작품 좋아요 수를 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "작품 좋아요 수 조회 성공")
    @GetMapping("/count/{artId}")
    public int getLikeCount(@PathVariable Long artId) {
        return artLikeService.getLikeCount(artId);
    }

//    좋아요 여부
    @Operation(summary = "작품 좋아요 여부 조회", description = "작품 좋아요 여부를 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "작품 좋아요 여부 조회 성공")
    @PostMapping("/liked")
    public ResponseEntity<Map<String, Object>> isLiked(@RequestBody ArtLikeVO artLikeVO) {
        Map<String, Object> response = new HashMap<>();
        boolean isLiked = artLikeService.getLiked(artLikeVO);
        if(isLiked) {
            response.put("message", "좋아요 여부 true");
            response.put("isLiked", isLiked);
            return ResponseEntity.ok(response);
        }
        response.put("message", "좋아요 여부 false");
        response.put("isLiked", isLiked);
        return ResponseEntity.ok(response);
    }

//    좋아요 삭제
    @Operation(summary = "작품 좋아요 삭제", description = "작품 좋아요를 삭제할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "작품 좋아요 삭제 성공")
    @DeleteMapping("delete/{artId}")
    public ResponseEntity<Map<String, Object>> deleteLike(@PathVariable Long artId, @RequestParam Long userId) {
        Map<String, Object> response = new HashMap<>();

        ArtLikeVO artLikeVO = new ArtLikeVO();
        artLikeVO.setArtId(artId);
        artLikeVO.setUserId(userId);

        try {
            artLikeService.remove(artLikeVO);
            response.put("message", "좋아요 취소 성공");
            response.put("status", artLikeVO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "좋아요 취소 실패");
            response.put("status", artLikeVO);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }
}
