package com.app.gradationback.mapper.sehyun;

import com.app.gradationback.domain.AuctionBiddingVO;
import com.app.gradationback.domain.AuctionDTO;
import com.app.gradationback.domain.AuctionVO;
import com.app.gradationback.mapper.AuctionBiddingMapper;
import com.app.gradationback.mapper.AuctionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
@Slf4j
public class AuctionBiddingMapperTest {

    @Autowired
    private AuctionBiddingMapper auctionBiddingMapper;

    @Test
    public void insert() {
        AuctionBiddingVO auctionBiddingVO = new AuctionBiddingVO();
        auctionBiddingVO.setAuctionBiddingPrice(250000);
        auctionBiddingVO.setAuctionBiddingAutoOk(false);
        auctionBiddingVO.setAuctionId(6L);
        auctionBiddingVO.setUserId(2L);
        auctionBiddingMapper.insert(auctionBiddingVO);
    }
}
