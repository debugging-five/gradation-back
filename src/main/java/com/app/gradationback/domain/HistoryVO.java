package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class HistoryVO {
    private Long id;
    private Date historyDate;
    private String historyContent;
    private Long userId;
}
