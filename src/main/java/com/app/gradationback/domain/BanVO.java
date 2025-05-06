package com.app.gradationback.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Data
@Schema(description = "정지 유저 정보")
public class BanVO {
    @Schema(description = "정지 ID", example = "1")
    private Long id;
    @Schema(description = "정지 사유", example = "게시판 내 광고성 글 다수 작성")
    private String banReason;
    @Schema(description = "정지 시간", example = "2025-05-05T15:30:00")
    private Timestamp banDate;
    @Schema(description = "정지 유저 ID", example = "10")
    private Long userId;
}
