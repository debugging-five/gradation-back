package com.app.gradationback.mapper;

import com.app.gradationback.domain.AuctionBiddingVO;
import com.app.gradationback.domain.AuctionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;


@Mapper
public interface AuctionBiddingMapper {

    public void insert(AuctionBiddingVO AuctionBiddingVO);
    public List<AuctionBiddingVO> selectAll(Long auctionId);
    public Optional<AuctionBiddingVO> select(Long auctionId);
    public List<AuctionBiddingVO> selectAutoAll(Long auctionId);
    public Optional<AuctionBiddingVO> selectAuto(Long auctionId);
//    경쟁 입찰자
    public Optional<Integer> selectCount(Long auctionId);
}
