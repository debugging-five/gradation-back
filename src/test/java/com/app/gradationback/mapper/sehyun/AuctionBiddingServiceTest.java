package com.app.gradationback.mapper.sehyun;

import com.app.gradationback.domain.AuctionBiddingVO;
import com.app.gradationback.service.AuctionService;
import com.app.gradationback.service.AuctionServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
@RequiredArgsConstructor
public class AuctionBiddingServiceTest {

    @Autowired
    public AuctionService auctionService;

    @Test
    public void insertTest() {
        AuctionBiddingVO auctionBiddingVO = new AuctionBiddingVO();
        auctionBiddingVO.setAuctionBiddingPrice(350000);
        auctionBiddingVO.setAuctionId(6L);
        auctionBiddingVO.setUserId(8L);

        auctionService.auctionBidding(auctionBiddingVO);

        log.info(auctionBiddingVO.toString());
    }

}
