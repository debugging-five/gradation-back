package com.app.gradationback.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "입찰 정보")
public class AuctionBiddingVO {
    private Long id;
    private int auctionBiddingPrice;
    private boolean auctionBiddingAutoOk;
    private String auctionBiddingTime;
    private Long auctionId;
    private Long userId;
}
