package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
@Data
public class ArtVO {
    private Long id;
    private String artTitle;
    private String artCategory;
    private String artMaterial;
    private String artSize;
    private String artDescription;
    private Date artEndDate;
    private Long userId;
}
