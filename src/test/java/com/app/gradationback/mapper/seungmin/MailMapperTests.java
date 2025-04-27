package com.app.gradationback.mapper.seungmin;

import com.app.gradationback.domain.MailDTO;
import com.app.gradationback.mapper.MailMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
            log.info("메일 제목: {}, 보낸 사람 닉네임: {}, 보낸 시간: {}",
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
            log.info("메일 제목: {}, 수신인 닉네임: {}, 보낸 시간: {}",
                    mail.getMailTitle(),
                    mail.getReceiveUserNickName(),
                    mail.getMailSendTime());
        }
    }


}