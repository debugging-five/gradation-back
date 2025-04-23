package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class ArtDTO {
//    TBL_ART
    private Long id;
    private String artTitle;
    private String artCategory;
    private String artMaterial;
    private String artSize;
    private String artDescription;
    private Date artEndDate;
    private Long userId;

//    TBL_ART_IMG
    private String artImgName;
    private String artImgPath;
    private Long artId;

//    TBL_USER
    private String userImgName;
    private String userImgPath;
    private String userName;
    private String userEmail;
    private String userIdentification;
    private String userPassword;
    private String userPhone;
    private String userNickName;
    private String userAddress;
    private String userDetailAddress;
    private String postalCode;
    private boolean userSnsOk;
    private boolean userMailOk;
    private boolean userAgreement;
    private String userIntroduce;
    private String userInstagram;
    private String userYoutube;
    private String userBlog;
    private String userKakao;
    private String userGoogle;
    private String userNaver;
    private boolean userAdminOk;
    private boolean userBanOk;
    private String userMajorImgName;
    private String userMajorImgPath;
    private String userWriterStatus;
    private String userUniversityStatus;
    private String userArtCategory;
    private String userBackgroundImgName;
    private String userBackgroundImgPath;
    private Long majorId;

//    TBL_HISTORY
    private Date historyDate;
    private String historyContent;
}
