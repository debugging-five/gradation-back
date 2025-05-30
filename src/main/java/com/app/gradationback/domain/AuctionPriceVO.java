package com.app.gradationback.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "입찰 가격")
public class AuctionPriceVO {
    private Long id;
    private Long auctionBiddingPrice;
    private Long AuctionBiddingMinimumPrice;
    private Long auctionId;
    private Long userId;
    private Integer bidderCount;
}
