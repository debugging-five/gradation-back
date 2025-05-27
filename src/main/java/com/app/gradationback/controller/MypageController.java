package com.app.gradationback.controller;

import com.app.gradationback.service.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/mypage/api/*")
public class MypageController {

    private final MypageService mypageService;

    // 회원 탈퇴 (연관 데이터 모두 삭제)
    @DeleteMapping("/withdraw")
    public ResponseEntity<String> withdrawUser(@RequestParam Long userId) {
        mypageService.withdrawAll(userId);
        return ResponseEntity.ok("회원 탈퇴가 정상적으로 처리되었습니다.");
    }


}
