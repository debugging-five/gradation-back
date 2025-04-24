package com.app.gradationback.mapper.sehyun;

import com.app.gradationback.domain.AuctionDTO;
import com.app.gradationback.mapper.AuctionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
@Slf4j
public class Test {
    private final AuctionMapper auctionMapper;

    public void selectAll() {
        List<AuctionDTO> auctions = auctionMapper.selectAll();
        for (AuctionDTO auction : auctions) {
            log.info(auction.toString());
        }
    }
}
