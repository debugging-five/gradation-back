package com.app.gradationback.controller;

import com.app.gradationback.domain.ArtistDTO;
import com.app.gradationback.service.ArtistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/artists/api/*")
public class ArtistController {

    private final ArtistService artistService;

//    artist(내 프로필)
    @Operation(summary = "작가리스트(내 프로필 조회)", description = "작가리스트에서 내 프로필을 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "내 프로필 조회 성공")
    @GetMapping("profile")
    public ResponseEntity<ArtistDTO> getMyArtistProfile(@RequestParam Long userId) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);

        return artistService.getMyArtistProfile(param)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    작가 리스트 조회
    @Operation(summary = "작가 리스트 조회", description = "작가 리스트를 조회할 수 있는 API")
    @Parameters({
            @Parameter(name = "userId", description = "조회 제외 대상 (로그인 유저)"),
            @Parameter(name = "category", description = "작품 카테고리", example = "서예"),
            @Parameter(name = "keyword", description = "작가 이름 검색", example = "김동건"),
            @Parameter(name = "order", description = "정렬 기준: name / recent", example = "name"),
            @Parameter(name = "direction", description = "오름차순", example = "asc"),
            @Parameter(name = "cursor", description = "페이지 번호", example = "1")
    })
    @ApiResponse(responseCode = "200", description = "작가 리스트 조회 성공")
    @GetMapping("list")
    public ResponseEntity<List<ArtistDTO>> getArtistList(@RequestParam Map<String, Object> param) {
        return ResponseEntity.ok(artistService.getArtistList(param));
    }

}
