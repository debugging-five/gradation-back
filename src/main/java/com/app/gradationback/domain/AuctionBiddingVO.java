package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Data
public class AuctionBiddingVO {
    private Long id;
    private int auctionBiddingPrice;
    private boolean auctionBiddingAutoOk;
    private Timestamp auctionBiddingTime;
    private Long auctionId;
    private Long userId;
}
