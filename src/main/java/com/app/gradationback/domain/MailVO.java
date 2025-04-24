package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Data
public class MailVO {
    private Long id;
    private String mailTitle;
    private String mailContent;
    private Timestamp mailSendTime;
    private boolean mailOpenOk;
    private int deletedBySender;
    private int deletedByReceiver;
    private Long sendUserId;
    private Long receiveUserId;
}
