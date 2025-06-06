package com.app.gradationback.controller;

import com.app.gradationback.domain.UpcyclingDTO;
import com.app.gradationback.domain.UpcyclingVO;
import com.app.gradationback.service.UpcyclingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upcycling/api")
public class UpcyclingController {

    private final UpcyclingService upcyclingService;
    private final UpcyclingDTO upcyclingDTO;

    // 전체 목록 조회
    @Operation(summary = "전체 신청 목록 조회", description = "업사이클링 신청 정보 전체를 조회할 수 있는 API")
    @GetMapping("list/{userId}")
    public List<UpcyclingDTO> getList(@PathVariable("userId") Long userId) {
        return upcyclingService.getUpcyclingUserList(userId);
    }

    // 단건 조회
    @Operation(summary = "단건 신청 조회", description = "업사이클링 신청 정보 중 ID 기준 단건 조회 할 수 있는 API")
    @Parameter(name = "id", description = "업사이클링 신청 ID", schema = @Schema(type = "number"), in = ParameterIn.PATH, required = true)
    @GetMapping("detail/{id}")
    public UpcyclingVO getDetail(@PathVariable Long id) {
        Optional<UpcyclingVO> foundId = upcyclingService.getByUpcyclingUser(id);
        return foundId.orElse(new UpcyclingVO());
    }

    // 신청 등록
    @Operation(summary = "업사이클링 신청 등록", description = "업사이클링 신청 정보를 등록하는 API")
    @ApiResponse(responseCode = "200", description = "신청 등록 성공")
    @PostMapping(value = "register", consumes = "multipart/form-data")
    public ResponseEntity<UpcyclingVO> register(@RequestPart("info") UpcyclingVO upcyclingVO, @RequestPart("file") MultipartFile file) {
        upcyclingService.register(upcyclingVO, file);
        return ResponseEntity.ok(upcyclingService.getByUpcyclingUser(upcyclingVO.getId()).orElse(new UpcyclingVO()));
    }

    // 상태 수정
    @Operation(summary = "업사이클링 신청 상태 수정", description = "업사이클링 신청 상태를 변경하는 API.")
    @ApiResponse(responseCode = "200", description = "상태 수정 성공")
    @PutMapping("modify/{id}")
    public void modify(@PathVariable Long id, @RequestBody UpcyclingVO upcyclingVO) {
        upcyclingVO.setId(id);
        upcyclingService.modify(upcyclingVO);
    }

    // 삭제
    @Operation(summary = "업사이클링 신청 삭제", description = "업사이클링 신청 정보를 삭제하는 API.")
    @ApiResponse(responseCode = "200", description = "삭제 성공")
    @DeleteMapping("remove/{id}")
    public void remove(@PathVariable Long id) {
        upcyclingService.remove(id);
    }
}

