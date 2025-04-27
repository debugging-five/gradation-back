package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class ExhibitionPastDTO {
//    TBL_EXHIBITION_PAST_ART
    private Long id;
    private Long gradationExhibitionId;
    private Long artId;

//    TBL_GRADATION_EXHIBITION
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

//    TBL_ART
    private String artTitle;
    private String artCategory;
    private String artMaterial;
    private String artSize;
    private String artDescription;
    private Date artEndDate;
    private String artStatus;
    private Long userId;

//    TBL_ART_IMG
    private String artImgName;
    private String artImgPath;
}
