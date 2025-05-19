package com.app.gradationback.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

@Component
@Data
public class ArtPostDTO {
//    TBL_ART
    @Schema(description = "작품 번호", example = "1")
    private Long id;
    @Schema(description = "작품 제목", required = true, example = "제목")
    private String artTitle;
    @Schema(description = "작품 카테고리", required = true, example = "한국화")
    private String artCategory;
    @Schema(description = "작품 재료", required = true, example = "유화 물감")
    private String artMaterial;
    @Schema(description = "작품 크기", required = true, example = "100 x 200 x 300")
    private String artSize;
    @Schema(description = "작품 설명", required = true, example = "작품 설명")
    private String artDescription;
    @Schema(description = "작품 제작 완료일", required = true, example = "2025-01-01")
    private Date artEndDate;
    @Schema(description = "작품 인증 여부", required = true, example = "미신청")
    private String artStatus;
    @Schema(description = "회원 번호", required = true, example = "1")
    private Long userId;

//    TBL_ART_POST
    @Schema(description = "작품 게시일", example = "2025-01-01T13:30:00")
    private Timestamp artPostDate;
    @Schema(description = "작품 번호", required = true, example = "1")
    private Long artId;

//    TBl_ART_IMG
    @Schema(description = "작품 이미지 제목", required = true, example = "art.jpg")
    private String artImgName;
    @Schema(description = "작품 이미지 경로", required = true, example = "assets/images/art.jpg")
    private String artImgPath;

//    TBL_ART_LIKE
    @Schema(description = "작품 좋아요 개수", example = "50")
    private int artLikeCount;
    @Schema(description = "댓글 개수", example = "50")
    private int commentCount;

//    TBL_COMMENT
//    @Schema(description = "댓글 내용", required = true, example = "댓글")
//    private String commentContent;
    @Schema(description = "게시글 번호", required = true, example = "1")
    private Long artPostId;

//    TBL_COMMENT_LIKE
    @Schema(description = "댓글 좋아요 누른 시간", example = "2025-01-01T13:30:00")
    private Timestamp commentLikeTime;
    @Schema(description = "댓글 번호", required = true, example = "1")
    private Long commentId;

//    TBL_USER
    @Schema(description = "회원 프로필 이미지 제목", required = true, example = "user.jpg")
    private String userImgName;
    @Schema(description = "회원 프로필 이미지 경로", required = true, example = "assets/images/user/profile.jpg")
    private String userImgPath;
    @Schema(description = "이름", required = true, example = "회원명")
    private String userName;
    @Schema(description = "이메일", required = true, example = "user@test.app")
    private String userEmail;
//    @Schema(description = "학생증 이미지 제목", example = "major.jpg")
//    private String userMajorImgName;
//    @Schema(description = "학생증 이미지 경로", example = "assets/images/user/major.jpg")
//    private String userMajorImgPath;
//    @Schema(description = "작가 배경화면 제목", example = "background.jpg")
//    private String userBackgroundImgName;
//    @Schema(description = "작가 배경화면 이름", example = "assets/images/user/background.jpg")
//    private String userBackgroundImgPath;
}
