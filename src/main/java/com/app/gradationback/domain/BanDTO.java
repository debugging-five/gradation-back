package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Data
public class BanDTO {
//    TBL_USER
    private Long id;
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
    private int userBanOk;
    private String userMajorImgName;
    private String userMajorImgPath;
    private String userWriterStatus;
    private String userUniversityStatus;
    private String userArtCategory;
    private String userBackgroundImgName;
    private String userBackgroundImgPath;
    private Long majorId;


//    TBL_BAN
    private String banReason;
    private Timestamp banDate;
    private Long userId;

}
