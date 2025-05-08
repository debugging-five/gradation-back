package com.app.gradationback.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SmsUtil {

    private final JavaMailSender JavaMailSender;

    //    이메일 발송
    public void sendEmail(String to, String subject, String content) throws MessagingException {
        MimeMessage mimeMessage = JavaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content);

        JavaMailSender.send(mimeMessage);
        log.info("이메일 전송 완료 : {}", to);

    }

}
