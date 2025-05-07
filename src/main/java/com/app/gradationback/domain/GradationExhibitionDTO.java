package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class GradationExhibitionDTO {
//    TBL_GRADATION_EXHIBITION
    private Long id;
    private String gradationExhibitionTitle;
    private String gradationExhibitionArt;
    private String gradationExhibitionCategory;
    private String gradationExhibitionTime;
    private String gradationExhibitionFee;
    private String gradationExhibitionTel;
    private String gradationExhibitionAddress;
    private String gradationExhibitionDate;

//    TBL_GRADATION_EXHIBITION_IMG
    private String gradationExhibitionImgName;
    private String gradationExhibitionImgPath;

////    TBL_EXHIBITION_PAST_ART
//    private Long gradationExhibitionId;
//    private Long artId;

////    TBL_ART
//    private String artTitle;
//
////    TBL_ART_IMG
//    private String artImgName;
//    private String artImgPath;
//
////    TBL_USER
//    private String userName;
}
