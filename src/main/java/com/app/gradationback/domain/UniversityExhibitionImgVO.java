package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UniversityExhibitionImgVO {
    private Long id;
    private String universityExhibitionImgName;
    private String universityExhibitionImgPath;
    private Long universityExhibitionId;
}
