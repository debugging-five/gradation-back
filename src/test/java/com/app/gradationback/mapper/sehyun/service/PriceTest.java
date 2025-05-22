package com.app.gradationback.mapper.sehyun.service;

import com.app.gradationback.mapper.AuctionBiddingMapper;
import com.app.gradationback.mapper.AuctionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
@Slf4j
public class PriceTest {
    @Autowired
    private AuctionBiddingMapper auctionBiddingMapper;

    @Autowired
    private AuctionMapper auctionMapper;

    @Test
    public void selectPriceTest() {
        log.info("{}", auctionBiddingMapper.selectPrice(48L));
    }

    @Test
    public void selectAuctionBiddingTest() {
        log.info("date: {}", auctionMapper.select(1L).toString());
    }
}
