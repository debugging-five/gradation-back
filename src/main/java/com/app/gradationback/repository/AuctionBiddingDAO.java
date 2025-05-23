package com.app.gradationback.repository;


import com.app.gradationback.domain.AuctionBiddingVO;
import com.app.gradationback.domain.AuctionPriceVO;
import com.app.gradationback.mapper.AuctionBiddingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuctionBiddingDAO {
    public final AuctionBiddingMapper auctionBiddingMapper;

    public void save(AuctionBiddingVO auctionBiddingVO) {
        auctionBiddingMapper.insert(auctionBiddingVO);
    }

    public List<AuctionBiddingVO> findAllListByAuctionId(Long auctionId) {
        return auctionBiddingMapper.selectAll(auctionId);
    }

    public Optional<AuctionBiddingVO> findByAuctionId(Long auctionId) {
        return auctionBiddingMapper.select(auctionId);
    }

    public List<AuctionBiddingVO> findAutoListByAuctionId(Long auctionId) {
        return auctionBiddingMapper.selectAutoAll(auctionId);
    }

    public Optional<AuctionBiddingVO> findAutoByAuctionId(Long auctionId) {
        return auctionBiddingMapper.selectAuto(auctionId);
    }

    public Optional<Integer> findCountByAuctionId(Long auctionId) {
        return auctionBiddingMapper.selectCount(auctionId);
    }

//    가격 조회
    public Optional<AuctionPriceVO> findPrice(Long auctionId) {
        return auctionBiddingMapper.selectPrice(auctionId);
    }

}
