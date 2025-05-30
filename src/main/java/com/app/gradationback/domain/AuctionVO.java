package com.app.gradationback.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
@Data
@Schema(description = "경매 정보")
public class AuctionVO {

    @Schema(description = "경매 번호", required = true, example = "1")
    private Long id;

    @Schema(description = "경매 시작 날짜", required = true, example = "2025-01-01 15:30:00")
    private String auctionStartDate;

    @Schema(description = "경매 종료 예정 날짜", required = true, example = "2025-01-04 15:30:00")
    private String auctionEndDate;

    @Schema(description = "시작 입찰가", required = true, example = "100000")
    private Long auctionStartPrice;

    @Schema(description = "최소추정가", required = true, example = "100000")
    private String auctionEstimatedMinPrice;

    @Schema(description = "최대추정가", required = true, example = "200000")
    private String auctionEstimatedMaxPrice;

    @Schema(description = "낙찰여부", example = "false")
    private boolean auctionAttracted;

    @Schema(description = "낙찰가", example = "200000")
    private Long auctionBidPrice;

    @Schema(description = "경매종료일", example = "2025-01-01 15:30:00")
    private String auctionBidDate;

    @Schema(description = "작품번호", required = true, example = "1")
    private Long artId;

    @Schema(description = "낙찰자", example = "1")
    private Long userId;
}
