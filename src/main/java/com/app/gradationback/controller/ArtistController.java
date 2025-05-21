package com.app.gradationback.controller;

import com.app.gradationback.domain.ArtistDTO;
import com.app.gradationback.domain.ArtistDetailDTO;
import com.app.gradationback.domain.HistoryVO;
import com.app.gradationback.domain.UserVO;
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
            @Parameter(name = "category", description = "작품 카테고리", example = "서예"),
            @Parameter(name = "keyword", description = "작가 이름 검색", example = "김동건"),
            @Parameter(name = "order", description = "정렬 기준: name / recent", example = "name"),
            @Parameter(name = "cursor", description = "페이지 번호", example = "1")
    })
    @ApiResponse(responseCode = "200", description = "작가 리스트 조회 성공")
    @PostMapping("list")
    public ResponseEntity<List<ArtistDTO>> getArtistList(@RequestBody Map<String, Object> params) {
        return ResponseEntity.ok(artistService.getArtistList(params));
    }

//    artist detail
    @Operation(summary = "작가 상세 조회", description = "작가 상세를 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "작가 상세 조회 성공")
    @Parameter(
            name = "userId",
            description = "조회할 작가 ID",
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("detail/{userId}")
    public ResponseEntity<ArtistDetailDTO> getArtistDetailById(@PathVariable Long userId) {
        ArtistDetailDTO artistDetailDTO = artistService.getArtistDetailById(userId);
        return ResponseEntity.ok(artistDetailDTO);
    }


    @Operation(summary = "작가 정보 수정", description = "작가 정보를 수정할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "작가 정보 수정 성공")
    @Parameter(
            name = "userId",
            description = "수정할 작가 ID",
            in = ParameterIn.PATH,
            required = true
    )
    @PatchMapping("detail/{userId}/modify")
    public ResponseEntity<UserVO> editArtist(@PathVariable Long userId, @RequestBody UserVO userVO) {
        userVO.setId(userId);
        artistService.editArtist(userVO);
        return ResponseEntity.ok(userVO);
    }

    @Operation(summary = "작가 이력 추가", description = "작가의 이력을 추가할 수 하는 API")
    @ApiResponse(responseCode = "200", description = "작가 이력 추가 성공")
    @Parameter(
            name = "userId",
            description = "추가할 작가 이력 ID",
            in = ParameterIn.PATH,
            required = true
    )
    @PostMapping("detail/{userId}/history")
    public ResponseEntity<HistoryVO> registerUserHistory(@PathVariable Long userId, @RequestBody HistoryVO historyVO) {
        historyVO.setUserId(userId);
        artistService.registerUserHistory(historyVO);
        return ResponseEntity.ok(historyVO);
    }

    @Operation(summary = "작가 이력 삭제", description = "작가의 이력을 삭제할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "작가 이력 삭제 성공")
    @Parameter(
            name = "historyId",
            description = "삭제할 작가 이력 ID",
            in = ParameterIn.PATH,
            required = true
    )
    @DeleteMapping("detail/{userId}/history/{historyId}")
    public void removeUserHistory(@PathVariable Long historyId) {
        artistService.removeUserHistory(historyId);
    }






}
