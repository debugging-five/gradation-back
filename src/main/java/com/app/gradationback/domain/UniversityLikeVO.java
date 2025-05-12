package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Data
public class UniversityLikeVO {
    private Long id;
    private Timestamp universityLikeTime;
    private Long universityExhibitionId;
    private Long userId;
}
