package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class AuctionVO {
    private Long id;
    private Date auctionStartDate;
    private int auctionStartPrice;
    private String auctionEstimatedMinPrice;
    private String auctionEstimatedMaxPrice;
    private boolean auctionAttracted;
    private int auctionBidPrice;
    private Date auctionBidDate;
    private Long artId;
    private Long userId;
}
