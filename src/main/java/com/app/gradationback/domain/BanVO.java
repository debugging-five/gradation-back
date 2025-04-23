package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Data
public class BanVO {
    private Long id;
    private String banReason;
    private Timestamp banDate;
    private Long userId;
}
