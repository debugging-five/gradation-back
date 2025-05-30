package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
@Data
public class UniversityExhibitionDTO {
//    TBL_UNIVERSITY(로고)
    private Long id;
    private String universityName;
    private String universityLocation;
    private String universityLogoImgName;
    private String universityLogoImgPath;
    private String universityHomepage;

//    TBL_UNIVERSITY_EXHIBITION
    private Date universityExhibitionStartDate;
    private Date universityExhibitionEndDate;
//    전시상태
    private String universityExhibitionState;
    private String universityExhibitionTitle;
    private String universityExhibitionLocation;
    private String universityExhibitionStatus;
    private String universityExhibitionRejectReason;
    private Date universityExhibitionRequestDate;
    private Long majorId;
//    드롭다운용 지역필터
    private String location;
//    검색
    private String keyword;

//    TBL_UNIVERSITY_EXHIBITION_IMG
    private String universityExhibitionImgName;
    private String universityExhibitionImgPath;
    private Long universityExhibitionId;

    private List<UniversityExhibitionImgVO> universityExhibitionImgList;

//    TBL_MAJOR
    private String majorName;
    private Long universityId;

//    TBL_UNIVERSITY_LIKE
    private Long universityLikeId;
    private Timestamp universityLikeTime;
    private Long userId;

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
