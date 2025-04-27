package com.app.gradationback.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Data
public class CommentVO {
    @Schema(description = "댓글 번호", example = "1")
    private Long id;
    @Schema(description = "댓글 내용", required = true, example = "댓글1")
    private String commentContent;
    @Schema(description = "댓글 작성 시간", example = "2025-05-01 13:10:30")
    private Timestamp commentDate;
    @Schema(description = "게시글 번호", required = true, example = "1")
    private Long artPostId;
    @Schema(description = "댓글 작성자", required = true, example = "1")
    private Long userId;
}
