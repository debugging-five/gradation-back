package com.app.gradationback.controller;

import com.app.gradationback.domain.UpcyclingVO;
import com.app.gradationback.service.UpcyclingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/upcycling/api/*")
public class UpcyclingController {

    private final UpcyclingService upcyclingService;

    // 전체 목록 조회
    @Operation(summary = "전체 신청 목록 조회", description = "업사이클링 신청 정보 전체를 조회 할 수 있는 API")
    @GetMapping("list")
    public List<UpcyclingVO> getList() {
        return upcyclingService.getAll();
    }

    // 단건 조회
    @Operation(summary = "단건 신청 조회", description = "업사이클링 신청 정보 중 ID 기준 단건 조회 할 수 있는 API")
    @Parameter(name = "id", description = "업사이클링 신청 ID", schema = @Schema(type = "number"), in = ParameterIn.PATH, required = true)
    @GetMapping("detail/{id}")
    public UpcyclingVO getDetail(@PathVariable Long id) {
        Optional<UpcyclingVO> foundId = upcyclingService.getById(id);
        return foundId.orElse(new UpcyclingVO());
    }

    // 신청 등록
    @Operation(summary = "업사이클링 신청 등록", description = "업사이클링 신청 정보를 등록하는 API")
    @ApiResponse(responseCode = "200", description = "신청 등록 성공")
    @PostMapping("register")
    public UpcyclingVO register(@RequestBody UpcyclingVO upcyclingVO) {
        log.info("신청 : {}", upcyclingVO);
        upcyclingService.register(upcyclingVO);
        return upcyclingService.getById(upcyclingVO.getId()).orElse(new UpcyclingVO());
    }
}
