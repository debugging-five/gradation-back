package com.app.gradationback.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
@Data
@Schema(description = "업사이클링 신청 정보 VO")
public class UpcyclingVO implements Serializable {
    @Schema(description = "업사이클링 신청 ID", example = "1")
    private Long id;
    @Schema(description = "주소", example = "서울시 강남구 테헤란로 123")
    private String upcyclingAddress;
    @Schema(description = "상세주소", example = "15층 1501호")
    private String upcyclingDetailAddress;
    @Schema(description = "이메일", example = "test@example.com")
    private String upcyclingEmail;
    @Schema(description = "연락처", example = "010-1234-5678")
    private String upcyclingPhone;
    @Schema(description = "수거 신청일", example = "2025-04-25")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date upcyclingDate;
    @Schema(description = "소형 갯수", example = "2")
    private Integer upcyclingSizeSmall;
    @Schema(description = "중형 갯수", example = "1")
    private Integer upcyclingSizeMedium;
    @Schema(description = "대형 갯수", example = "0")
    private Integer upcyclingSizeLarge;
    @Schema(description = "주된 재질 (콤마로 구분)", example = "캔버스,금속,플라스틱")
    private String upcyclingMaterials;
    @Schema(description = "이미지 파일명", example = "upcycle001.jpg")
    private String upcyclingImgName;
    @Schema(description = "이미지 경로", example = "public/images/upcycling")
    private String upcyclingImgPath;
    @Schema(description = "특이사항", example = "깨지기 쉬운 재질 포함")
    private String upcyclingSignificant;
    @Schema(description = "신청 상태", example = "미신청 / 신청완료")
    private String upcyclingStatus;
    @Schema(description = "신청자 User ID", example = "10")
    private Long userId;
}
