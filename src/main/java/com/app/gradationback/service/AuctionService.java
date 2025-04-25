package com.app.gradationback.service;

import com.app.gradationback.domain.AuctionBiddingVO;
import com.app.gradationback.domain.AuctionDTO;
import com.app.gradationback.domain.AuctionVO;

import java.util.HashMap;
import java.util.List;

public interface AuctionService {
    public void auctionRegistration(AuctionVO auctionVO);
    public List<AuctionDTO> auctionList(HashMap<String, Object> params);
    public List<AuctionDTO> auctionRead(Long id);
    public void auctionModify(AuctionVO auctionVO);
    public void auctionDelete(Long id);
// 입찰에 사용되는 로직
    public void auctionBidding(AuctionBiddingVO auctionBiddingVO);
}
