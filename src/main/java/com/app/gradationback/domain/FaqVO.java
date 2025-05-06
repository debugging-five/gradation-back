package com.app.gradationback.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema
public class FaqVO {
    @Schema(description = "FAQ ID", example = "1")
    private Long id;

    @Schema(description = "FAQ 질문 제목", example = "회원가입은 어떻게 하나요?")
    private String faqTitle;

    @Schema(description = "FAQ 답변 내용", example = "회원가입은 메인 페이지의 '회원가입' 버튼을 통해 가능합니다.")
    private String faqContent;

    @Schema(description = "FAQ 카테고리", example = "회원가입/로그인")
    private String faqCategory;

    @Schema(description = "작성자 User ID", example = "1")
    private Long userId;
}
