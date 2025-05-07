package com.app.gradationback.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface SmsService {

//    이메일 전송
    public ResponseEntity<Map<String, Object>> sendEmailVerification(String memberEmail);

//    인증코드 확인
    public boolean verifyAuthCode(String authCode);
}
