package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Data
public class QnaAnswerVO {
    private Long id;
    private String qnaAnswerTitle;
    private String qnaAnswerContent;
    private Timestamp qnaAnswerTime;
    private Long qnaId;
}
