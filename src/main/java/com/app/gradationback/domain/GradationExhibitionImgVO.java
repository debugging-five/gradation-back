package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class GradationExhibitionImgVO {
    private Long id;
    private String gradationExhibitionImgName;
    private String gradationExhibitionImgPath;
    private Long gradationExhibitionId;
}
