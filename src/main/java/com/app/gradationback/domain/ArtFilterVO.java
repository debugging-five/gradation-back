package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class ArtFilterVO {
    private String artCategory;
    private String sortBy;
    private int limit;
    private int offset;
}
