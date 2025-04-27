package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class GradationExhibitionVO {
    private Long id;
    private String gradationExhibitionTitle;
    private String gradationExhibitionArt;
    private String gradationExhibitionCategory;
    private String gradationExhibitionTime;
    private String gradationExhibitionFee;
    private String gradationExhibitionTel;
    private String gradationExhibitionAddress;
    private String gradationExhibitionDate;
}
