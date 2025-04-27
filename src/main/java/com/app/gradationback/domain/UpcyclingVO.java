package com.app.gradationback.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@Schema(description = "업사이클링 신청 정보 VO")
public class UpcyclingVO {
    @Schema(description = "업사이클링 ID", required = true, example = "1")
    private Long id;
    @Schema(description = "수거 품목의 재질", required = true, example = "금속")
    private String upcyclingCategory;
    @Schema(description = "수거 품목 사이즈 별 갯수", required = true, example = "소형 1")
    private String upcyclingSize;
    @Schema(description = "수거 날짜", required = true, example = "2025-04-24")
    private Date upcyclingDate;
    @Schema(description = "수거 주소", required = true, example = "서울시 강남구 테헤란로 123")
    private String upcyclingAddress;
    @Schema(description = "첨부 이미지 파일명", required = true, example = "upcycle01.jpg")
    private String upcyclingImgName;
    @Schema(description = "첨부 이미지 경로", required = true, example = "images/upcycling")
    private String upcyclingImgPath;
    @Schema(description = "특이사항", required = true, example = "도착 전 연락주세요.")
    private String upcyclingSignificant;
    @Schema(description = "신청 상태", required = true, example = "미신청 / 신청완료")
    private String upcyclingStatus;
    @Schema(description = "신청 유저 ID", required = true, example = "2")
    private Long userId;
}
