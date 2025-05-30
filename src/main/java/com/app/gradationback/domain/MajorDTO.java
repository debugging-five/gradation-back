package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class MajorDTO {
//    TBL_MAJOR
    private Long id;
    private String majorName;
    private Long universityId;

//    TBL_UNIVERSITY
    private String universityName;
    private String universityLocation;
    private String universityLogoImgName;
    private String universityLogoImgPath;
    private String universityHomepage;

//    TBL_UNIVERSITY_EXHIBITION
    private Date universityExhibitionStartDate;
    private Date universityExhibitionEndDate;
    private String universityExhibitionState;
    private String universityExhibitionTitle;
    private String universityExhibitionLocation;
    private String universityExhibitionStatus;
    private Long majorId;

//    TBL_UNIVERSITY_EXHIBITION_IMG
    private String universityExhibitionImgName;
    private String universityExhibitionImgPath;
    private Long universityExhibitionId;

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
    private boolean userAgreementOk;
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
    private String userProvider;
}

