package com.app.gradationback.mapper.sehyun.service;

import com.app.gradationback.mapper.AuctionBiddingMapper;
import com.app.gradationback.mapper.AuctionMapper;
import com.app.gradationback.repository.AuctionDAO;
import com.app.gradationback.service.AuctionService;
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

    @Autowired AuctionService auctionService;

    @Autowired
    private AuctionMapper auctionMapper;


    @Autowired
    private AuctionDAO auctionDAO;


    @Test
    public void selectPriceTest() {
//        log.info("{}", auctionService.getLatestPrice(48L));
        log.info("{}", auctionBiddingMapper.selectPrice(48L));
    }

    @Test
    public void selectAuctionBiddingTest() {
        log.info("date: {}", auctionMapper.select(1L).toString());
    }

    @Test
    public void auctionReadTest() {
//        log.info("date: {}", auctionMapper.select(1L).toString());
//        log.info("date: {}", auctionDAO.findById(1L).toString());
        log.info("date: {}", auctionService.auctionRead(1L).toString());
    }


}
