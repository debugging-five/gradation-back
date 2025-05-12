package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ArtistDTO {
    private Long id;
    private String userName;
    private String userImgName;
    private String userImgPath;
    private String userBackgroundImgName;
    private String userBackgroundImgPath;
    private String userArtCategory;
    private String universityName;
}
