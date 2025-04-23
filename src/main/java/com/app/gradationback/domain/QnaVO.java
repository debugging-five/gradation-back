package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Data
public class QnaVO {
    private Long id;
    private String qnaTitle;
    private String qnaContent;
    private Timestamp qnaTime;
    private String qnaCategory;
    private String qnaImgName;
    private String qnaImgPath;
    private Long userId;
}
