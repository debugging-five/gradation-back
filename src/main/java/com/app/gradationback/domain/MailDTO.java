package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Data
public class MailDTO {
//    TBL_USER (SEND)
    private Long id;
    private String sendUserImgName;
    private String sendUserImgPath;
    private String sendUserName;
    private String sendUserEmail;
    private String sendUserIdentification;
    private String sendUserPassword;
    private String sendUserPhone;
    private String sendUserNickName;
    private String sendUserAddress;
    private String sendUserDetailAddress;
    private String sendUserPostalCode;
    private boolean sendUserSnsOk;
    private boolean sendUserMailOk;
    private boolean sendUserAgreementOk;
    private String sendUserIntroduce;
    private String sendUserInstagram;
    private String sendUserYoutube;
    private String sendUserBlog;
    private String sendUserKakao;
    private String sendUserGoogle;
    private String sendUserNaver;
    private boolean sendUserAdminOk;
    private String userBanStatus;
    private String sendUserMajorImgName;
    private String sendUserMajorImgPath;
    private String sendUserWriterStatus;
    private String sendUserUniversityStatus;
    private String sendUserArtCategory;
    private String sendUserBackgroundImgName;
    private String sendUserBackgroundImgPath;
    private String sendUserProvider;
    private Long majorId;

//    TBL_USER (RECEIVE)
    private String receiveUserImgName;
    private String receiveUserImgPath;
    private String receiveUserName;
    private String receiveUserEmail;
    private String receiveUserIdentification;
    private String receiveUserPassword;
    private String receiveUserPhone;
    private String receiveUserNickName;
    private String receiveUserAddress;
    private String receiveUserDetailAddress;
    private String receiveUserpostalCode;
    private boolean receiveUserSnsOk;
    private boolean receiveUserMailOk;
    private boolean receiveUserAgreementOk;
    private String receiveUserIntroduce;
    private String receiveUserInstagram;
    private String receiveUserYoutube;
    private String receiveUserBlog;
    private String receiveUserKakao;
    private String receiveUserGoogle;
    private String receiveUserNaver;
    private boolean receiveUserAdminOk;
    private boolean receiveUserBanOk;
    private String receiveUserMajorImgName;
    private String receiveUserMajorImgPath;
    private String receiveUserWriterStatus;
    private String receiveUserUniversityStatus;
    private String receiveUserArtCategory;
    private String receiveUserBackgroundImgName;
    private String receiveUserBackgroundImgPath;
    private String receiveUserProvider;

//    TBL_MAIL
    private String mailTitle;
    private String mailContent;
    private Timestamp mailSendTime;
    private boolean mailOpenOk;
    private int deletedBySender;
    private int deletedByReceiver;
    private Long sendUserId;
    private Long receiveUserId;
}
