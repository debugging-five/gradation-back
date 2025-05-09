package com.app.gradationback.mapper.seungmin;

import com.app.gradationback.domain.MailDTO;
import com.app.gradationback.mapper.MailMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class MailMapperTests {

    @Autowired
    private MailMapper mailMapper;

//      쪽지 등록
    @Test
    public void insertTest() {
        MailDTO mailDTO = new MailDTO();
        mailDTO.setMailTitle("안녕하세요");
        mailDTO.setMailContent("반갑습니다잉");
        mailDTO.setSendUserId(2L);
        mailDTO.setReceiveUserEmail("minsuyang@daum.net");
        mailMapper.insert(mailDTO);
    }

//      쪽지 수신함
    @Test
    public void selectReceivedTest() {
        List<MailDTO> receivedList = mailMapper.selectReceived(2L);
        for (MailDTO mail : receivedList) {
            log.info("제목: {}, 이름: {}, 작성일: {}",
                    mail.getMailTitle(),
                    mail.getSendUserName(),
                    mail.getMailSendTime());
        }
    }

//      보낸 쪽지함
    @Test
    public void selectSendedTest() {
        List<MailDTO> sendedList = mailMapper.selectSended(2L);
        for (MailDTO mail : sendedList) {
            log.info("제목: {}, 이름: {}, 작성일: {}",
                    mail.getMailTitle(),
                    mail.getReceiveUserName(),
                    mail.getMailSendTime());
        }
    }

//    받은 쪽지
    @Test
    public void selectReceivedDetailTest() {
        Optional<MailDTO> Mail = mailMapper.selectReceivedDetail(8L, 5L);

        if (Mail.isPresent()) {
            MailDTO mail = Mail.get();
            log.info(": {}", mail.getMailTitle());
            log.info(": {}", mail.getMailContent());
            log.info("발신자: {}", mail.getSendUserNickName());
            log.info("메일주소: {}", mail.getSendUserEmail());
            log.info(": {}", mail.getMailSendTime());
        } else {
            log.info("해당 쪽지를 찾을 수 없습니다.");
        }
    }

//    내가 보낸쪽지
    @Test
    public void selectSendedDetailTest() {
        Optional<MailDTO> Mail = mailMapper.selectSendedDetail(3L, 2L);

        if (Mail.isPresent()) {
            MailDTO mail = Mail.get();
            log.info(": {}", mail.getMailTitle());
            log.info(": {}", mail.getMailContent());
            log.info("수신자: {}", mail.getReceiveUserNickName());
            log.info("메일주소: {}", mail.getReceiveUserEmail());
            log.info(": {}", mail.getMailSendTime());
        } else {
            log.info("해당 쪽지를 찾을 수 없습니다.");
        }
    }

//    내가 보낸 쪽지 삭제
    @Test
    public void deleteSendedMailTest() {
        mailMapper.deleteSendedMail(41L, 2L);
    }

//    받은 쪽지 삭제
    @Test
    public void deleteReceivedMailTest(){
        mailMapper.deleteReceivedMail(41L, 12L);
    }

    @Test
    public void selectAlertTest() {
        List<MailDTO> receivedList = mailMapper.selectAlert(12L);
        for (MailDTO mail : receivedList) {
            log.info("제목: {}, 발신인: {}, 작성일: {}",
                    mail.getMailTitle(),
                    mail.getSendUserName(),
                    mail.getMailSendTime());
        }
    }

//    @Test
//    public void selectAlertDetailTest() {
//        MailDTO mailDTO = new MailDTO();
//        mailDTO.setId(42L);
//        mailDTO.setReceiveUserId(12L);
//
//        Optional<MailDTO> mailOptional = mailMapper.selectAlertDetail(mailDTO);
//
//        if (mailOptional.isPresent()) {
//            MailDTO mail = mailOptional.get();
//            log.info(": {}", mail.getMailTitle());
//            log.info(": {}", mail.getMailContent());
//            log.info(": {}", mail.getMailSendTime());
//        } else {
//            log.info("해당 쪽지를 찾을 수 없습니다.");
//        }
//    }



}