package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Data
public class ArtPostVO {
    private Long id;
    private Timestamp artPostDate;
    private Long userId;
    private Long artId;
}
