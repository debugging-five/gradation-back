package com.app.gradationback.controller;

import com.app.gradationback.service.ApprovalService;
import com.app.gradationback.util.AdminCheckUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/api/approval")
@Slf4j
public class ApprovalController {
    private final ApprovalService approvalService;
    private final AdminCheckUtil adminCheckUtil;

//    승 인 대기 목록 조회
    @Operation(summary = "승인 대기 목록 조회", description = "관리자 전용: 업사이클링/작가/대학교/작품 승인 대기 목록 조회")
    @GetMapping("/{type}/pending")
//    승인 카테고리 타입이 각각 다르므로 리스트 와일드 카드로, 패스베리어블 스트링 타입으로 항목 확인학
    public List<?> getPendingList(@PathVariable String type, HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("그라데이션 관리자만 접근 가능합니다.");
        }
        return approvalService.getPendingList(type);
    }

    //    승 인 완료 목록 조회
    @Operation(summary = "승인 완료 목록 조회", description = "관리자 전용: 업사이클링/작가/대학교/작품 승인 완료 목록 조회")
    @GetMapping("/{type}/completed")
//    승인 카테고리 타입이 각각 다르므로 리스트 와일드 카드로, 패스베리어블 스트링 타입으로 항목 확인학
    public List<?> getCompletedList(@PathVariable String type, HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("그라데이션 관리자만 접근 가능합니다.");
        }
        return approvalService.getCompletedList(type);
    }

//    승인 대기 중 상세 목록 조회
    @Operation(summary = "승인 대기 중 상세 목록 조회", description = "관리자 전용: 특정 항목 상세 조회")
    @GetMapping("/{type}/pending/{id}")
//    결과값 유무로 옵셔널, 얘도 뭐 들어올지 모르니 와일드 카드
    public Optional<?> getPendingById(@PathVariable String type, @PathVariable Long id, HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("그라데이션 관리자만 접근 가능합니다.");
        }
        return approvalService.getPendingById(type, id);
    }

    //    승인 대기 중 상세 목록 조회
    @Operation(summary = "승인 대기 중 상세 목록 조회", description = "관리자 전용: 특정 항목 상세 조회")
    @GetMapping("/{type}/pending/{id}")
//    결과값 유무로 옵셔널, 얘도 뭐 들어올지 모르니 와일드 카드
    public Optional<?> getCompletedById(@PathVariable String type, @PathVariable Long id, HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("그라데이션 관리자만 접근 가능합니다.");
        }
        return approvalService.getCompletedById(type, id);
    }

//    승인 상태 변경(승인, 반려)
    @Operation(summary = "승인 상태 변경", description = "관리자 전용: 승인 또는 반려 처리 (업사이클링/작가/대학교/작품)")
    @PatchMapping("/{type}/status")
//    ResponseEntity로 상태코드, 헤더, 본문 직접 조절, 응답
//    String - 요청 대상 구분, dto - 요청 받은 본문 데이터
    public ResponseEntity<String> updateApprovalStatus(@PathVariable String type, @RequestBody Object dto, HttpServletRequest request) {
        if (!adminCheckUtil.isAdmin(request)) {
            throw new RuntimeException("그라데이션 관리자만 접근 가능합니다.");
        }
        approvalService.updateStatus(type, dto);
        return ResponseEntity.ok("승인 상태가 정상적으로 변경되었습니다.");
    }
}
