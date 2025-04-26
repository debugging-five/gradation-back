package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Data
public class CommentLikeVO {
    private Long id;
    private Timestamp commentLikeTime;
    private Long commentId;
    private Long userId;
}
