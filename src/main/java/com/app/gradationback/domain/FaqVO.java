package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class FaqVO {
    private Long id;
    private String faqTitle;
    private String faqContent;
    private String faqCategory;
}
