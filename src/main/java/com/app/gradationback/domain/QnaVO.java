package com.app.gradationback.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Data
@Schema(description = "QNA")
public class QnaVO {
    @Schema(description = "QNA ID", example = "1")
    private Long id;
    @Schema(description = "제목", example = "회원탈퇴는 어떻게 진행하나요?")
    private String qnaTitle;
    @Schema(description = "내용", example = "회원탈퇴를 하고 싶습니다. 어떻게 하면 될까요?")
    private String qnaContent;
    @Schema(description = "작성 시간", example = "2025-05-05T15:30:00")
    private Timestamp qnaTime;
    @Schema(description = "구분", example = "개인정보 관련")
    private String qnaCategory;
    @Schema(description = "첨부파일 이름", example = "screenshot.jpg")
    private String qnaImgName;
    @Schema(description = "첨부파일 경로", example = "/images/category")
    private String qnaImgPath;
    @Schema(description = "문의 유저 ID", example = "10")
    private Long userId;
}
