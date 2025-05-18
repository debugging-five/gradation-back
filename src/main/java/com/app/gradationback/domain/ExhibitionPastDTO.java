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
    private String gradationExhibitionDate;

//    TBL_ART
    private String artTitle;

//    TBL_ART_IMG
    private String artImgName;
    private String artImgPath;

//    TBL_USER
    private String userName;
}
