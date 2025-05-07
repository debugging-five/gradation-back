//package com.app.gradationback.util;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import net.nurigo.sdk.NurigoApp;
//import net.nurigo.sdk.message.model.Message;
//import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
//import net.nurigo.sdk.message.response.SingleMessageSentResponse;
//import net.nurigo.sdk.message.service.DefaultMessageService;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//@Slf4j
//public class SmsUtil {
//
//    private final JavaMailSender javaMailSender;
//
//
//    private final JavaMailSender JavaMailSender;
//    private DefaultMessageService messageService;
//
//    @PostConstruct
//    //    이메일 발송
//    public void sendEmail(String to, String subject, String content) throws MessagingException {
//        MimeMessage mimeMessage = JavaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//
//        helper.setTo(to);
//        helper.setSubject(subject);
//        helper.setText(content);
//
//        javaMailSender.send(mimeMessage);
//        log.info("이메일 전송 완료 : {}", to);
//
//    }
//
//}
