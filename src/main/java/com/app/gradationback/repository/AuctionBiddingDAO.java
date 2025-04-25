package com.app.gradationback.repository;


import com.app.gradationback.domain.AuctionBiddingVO;
import com.app.gradationback.mapper.AuctionBiddingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuctionBiddingDAO {
    public final AuctionBiddingMapper auctionBiddingMapper;

    public void insert(AuctionBiddingVO auctionBiddingVO) {
        auctionBiddingMapper.insert(auctionBiddingVO);
    }
}
