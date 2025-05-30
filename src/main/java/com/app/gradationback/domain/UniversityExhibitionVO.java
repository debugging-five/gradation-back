package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class UniversityExhibitionVO {
    private Long id;
    private Date universityExhibitionStartDate;
    private Date universityExhibitionEndDate;
    private String universityExhibitionTitle;
    private String universityExhibitionLocation;
    private String universityExhibitionStatus;
    private String universityExhibitionRejectReason;
    private Date universityExhibitionRequestDate;
    private Long majorId;
}
