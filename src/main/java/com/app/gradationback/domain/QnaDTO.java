package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Data
public class QnaDTO {
//    TBL_QNA
    private Long id;
    private String qnaTitle;
    private String qnaContent;
    private Timestamp qnaTime;
    private String qnaCategory;
    private String qnaImgName;
    private String qnaImgPath;
    private Long userId;

//    TBL_QNA_ANSWER
    private String qnaAnswerTitle;
    private String qnaAnswerContent;
    private Timestamp qnaAnswerTime;
    private Long qnaId;

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

}
