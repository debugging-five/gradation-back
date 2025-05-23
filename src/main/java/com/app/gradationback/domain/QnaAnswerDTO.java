package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class QnaAnswerDTO {
    private Long qnaId;
    private String qnaAnswerTitle;
    private String qnaAnswerContent;
}