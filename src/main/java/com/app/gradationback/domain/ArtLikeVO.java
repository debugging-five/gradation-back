package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Data
public class ArtLikeVO {
    private Long id;
    private Timestamp artLikeTime;
    private Long artId;
    private Long userId;
}
