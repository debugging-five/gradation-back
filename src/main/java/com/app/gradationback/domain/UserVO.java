package com.app.gradationback.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class UserVO {
    @Schema(description = "회원 번호", required = true, example = "1")
    private Long id;
    @Schema(description = "회원 프로필 이미지 제목", required = true, example = "user.jpg")
    private String userImgName;
    @Schema(description = "회원 프로필 이미지 경로", required = true, example = "assets/images/user/profile")
    private String userImgPath;
    @Schema(description = "이름", required = true, example = "회원명")
    private String userName;
    @Schema(description = "이메일", required = true, example = "user@test.app")
    private String userEmail;
    @Schema(description = "아이디", required = true, example = "user1234")
    private String userIdentification;
    @Schema(description = "비밀번호", required = true, example = "user123!@#")
    private String userPassword;
    @Schema(description = "전화번호", required = true, example = "010-1234-5678")
    private String userPhone;
    @Schema(description = "닉네임", example = "닉네임")
    private String userNickName;
    @Schema(description = "주소", example = "주소")
    private String userAddress;
    @Schema(description = "상세 주소", example = "상세주소")
    private String userDetailAddress;
    @Schema(description = "우편번호", example = "12345")
    private String userPostalCode;
    @Schema(description = "SNS 수신 여부", example = "1")
    private boolean userSnsOk;
    @Schema(description = "메일 수신 여부", example = "1")
    private boolean userMailOk;
    @Schema(description = "약관 동의 여부", example = "1")
    private boolean userAgreementOk;
    @Schema(description = "작가 소개", example = "작가 소개")
    private String userIntroduce;
    @Schema(description = "인스타그램 계정", example = "@user")
    private String userInstagram;
    @Schema(description = "유튜브 계정", example = "@user")
    private String userYoutube;
    @Schema(description = "블로그 계정", example = "https://blog.com/")
    private String userBlog;
    @Schema(description = "카카오 계정")
    private String userKakao;
    @Schema(description = "구글 계정")
    private String userGoogle;
    @Schema(description = "네이버 계장")
    private String userNaver;
    @Schema(description = "관리자 여부", example = "1")
    private boolean userAdminOk;
    @Schema(description = "정지 여부", example = "1")
    private int userBanOk;
    @Schema(description = "학생증 이미지 제목", example = "major.jpg")
    private String userMajorImgName;
    @Schema(description = "학생증 이미지 경로", example = "assets/images/user/major.jpg")
    private String userMajorImgPath;
    @Schema(description = "재학중인 대학명", example = "00대학교")
    private String universityName;
    @Schema(description = "재학중인 대학의 학과명", example = "00학과")
    private String majorName;
    @Schema(description = "작가 인증 상태", example = "미신청")
    private String userWriterStatus;
    @Schema(description = "학생증 인증 상태", example = "미신청")
    private String userUniversityStatus;
    @Schema(description = "인증 신청 일자", example = "2025-05-26")
    private Date userUniversityDate;
    @Schema(description = "작가 작품 분야", example = "회화")
    private String userArtCategory;
    @Schema(description = "작가 배경화면 제목", example = "background.jpg")
    private String userBackgroundImgName;
    @Schema(description = "작가 배경화면 이름", example = "assets/images/user/background.jpg")
    private String userBackgroundImgPath;
    @Schema(description = "학과", example = "1")
    private String userProvider;
    @Schema(description = "재학중인 대학교명", example = "00대학교")
    private String userMyUniversity;
    @Schema(description = "재학중인 대학의 학과명", example = "00학과")
    private String userMyMajor;
    @Schema(description = "학과 아이디", example = "2")
    private Long majorId;
    @Schema(description = "학교 인증 기각 사유", example = "첨부파일의 사진의 글씨를 알아 볼 수 없습니다.")
    private String userUniversityRejectReason;
    @Schema(description = "학교 인증 신청일", example = "2025-05-05")
    private Date userUniversityRequestDate;
}
