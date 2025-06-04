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
    public ResponseEntity<Map<String, Object>> getArtistList(@RequestBody Map<String, Object> params) {
        Map<String, Object> response = new HashMap<>();

        try {
            List<ArtistDTO> artistList = artistService.getArtistList(params);

            response.put("posts", artistList);
            response.put("params", params);

            if (artistList != null && !artistList.isEmpty()) {
                response.put("message", "작가 리스트 조회 성공");
                response.put("contents", artistService.getCountArtistList(params));
            } else {
                response.put("message", "작가 리스트 조회 실패");
                response.put("contents", 0);
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("message", "서버 오류: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

//    artist detail
    @Operation(summary = "작가 상세 조회", description = "작가 상세를 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "작가 상세 조회 성공")
    @Parameters({
            @Parameter(name = "userId", description = "user ID", example = "1"),
            @Parameter(name = "cursor", description = "페이지", example = "1")
    })
    @GetMapping("detail/{userId}")
    public ResponseEntity<ArtistDetailDTO> getArtistDetailById(@PathVariable Long userId) {
        ArtistDetailDTO artistDetailDTO = artistService.getArtistDetailById(userId);
        return ResponseEntity.ok(artistDetailDTO);
    }

//    artist detail artList
    @Operation(summary = "작가 상세 작품 조회", description = "작가 상세페이지에서 작품들을 조회할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "작가 상세페이지 작품 조회 성공")
    @GetMapping("/detail/{userId}/arts")
    public ResponseEntity<Map<String, Object>> getArtistArtList(@PathVariable Long userId, @RequestParam Integer cursor) {
        Map<String, Object> response = new HashMap<>();
        response.put("userId", userId);
        response.put("cursor", cursor);

        try {
            List<ArtistDetailDTO> artistArtsList = artistService.getArtistArtsList(response);

            if(artistArtsList != null) {
                response.put("arts", artistArtsList);
                response.put("message", "작품 조회 성공");
                response.put("contents", artistService.getCountArtistArts(userId));
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "작품 조회 실패");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }
        } catch (Exception e) {
            response.put("message", "서버 오류: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

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
