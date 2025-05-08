package com.app.gradationback.service;

import com.app.gradationback.util.SmsUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    private final JavaMailSender javaMailSender;
    private final SmsUtil smsUtil;
    private final Map<String, String> codeMap = new ConcurrentHashMap<>();

    //    이메일 전송
    @Override
    public ResponseEntity<Map<String, Object>> sendEmailVerification(String memberEmail) {
        Map<String, Object> response = new HashMap<>();

        String verificationCode = String.format("%06d", (int)(Math.random() * 900000) + 100000);
    //        session.setAttribute("verificationCode", verificationCode);

        String emailSubject = "[Gradation] 이메일 인증코드";
        String emailContent = "안녕하세요, 아래 인증코드를 입력하세요\n["+ verificationCode +"]";
        codeMap.put("verificationCode", verificationCode);

        try {
            smsUtil.sendEmail(memberEmail, emailSubject, emailContent);
            response.put("message", "인증 코드가 전송되었습니다");
            response.put("verificationCode", verificationCode);
        } catch (MessagingException e) {
            response.put("message", "인증 코드 전송에 실패하였습니다");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.ok(response);
    }

//    인증코드 검증
    @Override
    public boolean verifyAuthCode(String authCode) {
        String code = authCode != null ? authCode.replaceAll("\"", "") : null;
        log.info(codeMap.get("verificationCode"));
        log.info(code);
        return codeMap.get("verificationCode").equals(code);
    }
}
