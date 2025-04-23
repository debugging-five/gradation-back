package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
@Data
public class UniversityDTO {
    //	TBL_UNIVERSITY
    private Long id;
    private String universityName;
    private String universityLocation;
    private String universityLogoImgName;
    private String universityLogoImgPath;
    private String universityHomepage;

    //	TBL_UNIVERSITY_LIKE
    private Timestamp universityLikeTime;
    private Long universityId;
    private Long userId;

    //	TBL_UNIVERSITY_EXHIBITION
    private Date universityExhibitionStartDate;
    private Date universityExhibitionEndDate;
    private String universityExhibitionState;
    private String universityExhibitionTitle;
    private String universityExhibitionLocation;
    private String universityExhibitionStatus;
    private Long majorId;

    //	TBL_UNIVERSITY_EXHIBITION_IMG
    private String universityExhibitionImgName;
    private String universityExhibitionImgPath;
    private Long universityExhibitionId;
}
