package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class GradationExhibitionVO {
    private Long id;
    private String gradationExhibitionTitle;
    private int gradationExhibitionCount;
    private Date gradationExhibitionTime;
    private int gradationExhibitionFee;
    private String gradationExhibitionTel;
    private String gradationExhibitionAddress;
    private Date gradationExhibitionStartDate;
    private Date gradationExhibitionEndDate;
    private String gradationExhibitionAddressImgName;
    private String gradationExhibitionTitleImgPath;
}
