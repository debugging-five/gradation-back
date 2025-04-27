package com.app.gradationback.repository;


import com.app.gradationback.domain.AuctionBiddingVO;
import com.app.gradationback.mapper.AuctionBiddingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuctionBiddingDAO {
    public final AuctionBiddingMapper auctionBiddingMapper;

    public void insert(AuctionBiddingVO auctionBiddingVO) {
        auctionBiddingMapper.insert(auctionBiddingVO);
    }

    public List<AuctionBiddingVO> selectAll(Long auctionId) {
        return auctionBiddingMapper.selectAll(auctionId);
    }

    public Optional<AuctionBiddingVO> select(Long auctionId) {
        return auctionBiddingMapper.select(auctionId);
    }

    public List<AuctionBiddingVO> selectAutoAll(Long auctionId) {
        return auctionBiddingMapper.selectAutoAll(auctionId);
    }

    public Optional<AuctionBiddingVO> selectAuto(Long auctionId) {
        return auctionBiddingMapper.selectAuto(auctionId);
    }

}
