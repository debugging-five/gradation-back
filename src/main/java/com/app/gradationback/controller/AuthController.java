package com.app.gradationback.controller;

import com.app.gradationback.service.SmsService;
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
@RequestMapping("/auth/*")
public class AuthController {

    private final SmsService smsService;

//    이메일 전송
    @Operation(summary = "이메일로 인증번호 전송", description = "이메일로 인증번호를 전송할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "인증번호 전송 성공")
    @PostMapping("sendEmail")
    public ResponseEntity<Map<String, Object>> sendEmail(@RequestBody String memberEmail){
        log.info(memberEmail);
        return smsService.sendEmailVerification(memberEmail);
    }

//    인증코드 검증
    @Operation(summary = "인증번호 검증", description = "인증번호를 검증할 수 있는 API")
    @ApiResponse(responseCode = "200", description = "인증번호 검증 성공")
    @PostMapping("verifyCode")
    public ResponseEntity<Map<String, Object>> verifyCode(@RequestBody String code){
        Map<String,Object> response = new HashMap<>();
        log.info("verifyCode {}", smsService.verifyAuthCode(code));

        boolean isFlag = smsService.verifyAuthCode(code);
        if(isFlag){
            response.put("message", "인증이 완료되었습니다");
            response.put("isFlag", isFlag);
            return ResponseEntity.ok(response);
        }else{
            response.put("message", "인증번호를 확인해주세요😅");
            response.put("isFlag", isFlag);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
