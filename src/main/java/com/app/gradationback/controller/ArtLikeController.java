package com.app.gradationback.controller;

import com.app.gradationback.domain.ArtLikeVO;
import com.app.gradationback.service.ArtLikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public void registerLike(@RequestBody ArtLikeVO artLikeVO) {
        artLikeService.register(artLikeVO);
    }

//    좋아요 수
    @Operation(summary = "작품 좋아요 수 조회", description = "작품 좋아요 수를 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "작품 좋아요 수 조회 성공")
    @GetMapping("/count/{artId}")
    public int getLikeCount(@PathVariable Long artId) {
        return artLikeService.getLikeCount(artId);
    }

//    좋아요 삭제
    @Operation(summary = "작품 좋아요 삭제", description = "작품 좋아요를 삭제할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "작품 좋아요 삭제 성공")
    @DeleteMapping("delete")
    public void deleteLike(@RequestBody ArtLikeVO artLikeVO) {
        artLikeService.remove(artLikeVO);
    }


}
