package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
@Data
public class CommentDTO {
    private Long id;
    private Long artPostId;
    private Long userId;
    private String commentContent;
    private Timestamp commentDate;
    private Integer commentLikeCount;
    private String userName;
    private String userImgPath;
    private String userImgName;
    private String userWriterStatus;
}
