package com.app.gradationback.service;

import com.app.gradationback.domain.AuctionBiddingVO;
import com.app.gradationback.domain.AuctionDTO;
import com.app.gradationback.domain.AuctionPriceVO;
import com.app.gradationback.domain.AuctionVO;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface AuctionService {

//    경매 조회에 사용되는 로직
    public void auctionRegistration(AuctionVO auctionVO);
    public List<AuctionDTO> auctionList(HashMap<String, Object> params);
    public Optional<AuctionDTO> auctionRead(Long id);
    public List<AuctionDTO> auctionFooterBidding(Integer cursor);
    public Long auctionFindByArtId(Long artId);
    public void auctionModify(AuctionVO auctionVO);
    public void auctionDelete(Long id);
// 입찰에 사용되는 로직
    public void auctionBidding(AuctionBiddingVO auctionBiddingVO);
    public void endBidding(AuctionVO auctionVO);
    public Optional<AuctionBiddingVO> auctionStatus(Long auctionId);
    public Optional<Integer> auctionBidderCount(Long auctionId);
    public Optional<AuctionPriceVO> getLatestPrice(Long auctionId);
}
