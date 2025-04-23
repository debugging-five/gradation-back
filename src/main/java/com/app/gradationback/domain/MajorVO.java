package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MajorVO {
    private Long id;
    private String majorName;
    private Long universityId;
}
