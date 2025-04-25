package com.app.gradationback.mapper;

import com.app.gradationback.domain.AuctionBiddingVO;
import com.app.gradationback.domain.AuctionVO;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AuctionBiddingMapper {

    public void insert(AuctionBiddingVO AuctionBiddingVO);
}
