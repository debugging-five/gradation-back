package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Data
public class ReplyLikeVO {
    private Long id;
    private Timestamp replyLikeTime;
    private Long replyId;
    private Long userId;
}
