package com.app.gradationback.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema
public class FaqVO {

    private Long id;

    private String faqTitle;

    private String faqContent;

    private String faqCategory;

    private Long userId;
}
