package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ArtImgVO {
    private Long id;
    private String artImgName;
    private String artImgPath;
    private Long artId;
}
