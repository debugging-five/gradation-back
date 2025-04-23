package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UniversityVO {
    private Long id;
    private String universityName;
    private String universityLocation;
    private String universityLogoImgName;
    private String universityLogoImgPath;
    private String universityHomepage;
}
