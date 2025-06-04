package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Data
public class ArtistDetailDTO {
    private Long id;
    private String userImgName;
    private String userImgPath;
    private String userName;
    private String userEmail;
    private String universityName;
    private String userInstagram;
    private String userBlog;
    private String userYoutube;
    private String userIntroduce;
    private String userArtCategory;

    private Date historyDate;
    private String historyContent;
    private Long historyId;

    private Long artPostId;
    private String artImgPath;
    private String artImgName;
    private Long artId;

    private String artTitle;

    private List<HistoryVO> historyList;
    private List<ArtImgVO> artImgList;
}
