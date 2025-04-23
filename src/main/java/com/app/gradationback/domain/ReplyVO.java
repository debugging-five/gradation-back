package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Data
public class ReplyVO {
    private Long id;
    private String replyContent;
    private Timestamp replyDate;
    private Long artPostId;
    private Long userId;
}
