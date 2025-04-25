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

    @Test
    public void insertTest() {
        MailDTO mailDTO = new MailDTO();
        mailDTO.setMailTitle("안녕하세요");
        mailDTO.setMailContent("반갑습니다잉");
        mailDTO.setSendUserId(4L);
        mailDTO.setReceiveUserEmail("iseonghyeon@gmail.com");
        mailMapper.insert(mailDTO);
    }

//      쪽지 수신함
    @Test
    public void selectReceivedTest() {
        List<MailDTO> receivedList = mailMapper.selectReceived(4L);
        for (MailDTO mail : receivedList) {
            log.info("메일 제목: {}, 발신인: {}, 작성일: {}",
                    mail.getMailTitle(),
                    mail.getSendUserNickName(),
                    mail.getMailSendTime());
        }
    }
//      보낸 쪽지함
    @Test
    public void selectSendedTest() {
        List<MailDTO> sendedList = mailMapper.selectSended(2L);
        for (MailDTO mail : sendedList) {
            log.info("제목: {}, 수신인: {}, 작성일: {}",
                    mail.getMailTitle(),
                    mail.getReceiveUserNickName(),
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





}