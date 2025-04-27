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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

    @Test
    public void select() {
        List<AuctionBiddingVO> biddings = auctionBiddingMapper.selectAll(6L);
        for (AuctionBiddingVO auctionBiddingVO : biddings) {
            log.info(auctionBiddingVO.toString());
        }
    }
    
    @Test
    public void selectAll() {
        Optional<AuctionBiddingVO> foundBidding = auctionBiddingMapper.select(6L);
        if (foundBidding.isPresent()) {
            log.info(foundBidding.get().toString());
        }
        log.info("낙찰자 없는경우");
    }
}
