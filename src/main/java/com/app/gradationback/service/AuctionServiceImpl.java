package com.app.gradationback.service;

import com.app.gradationback.domain.AuctionBiddingVO;
import com.app.gradationback.domain.AuctionDTO;
import com.app.gradationback.domain.AuctionVO;
import com.app.gradationback.repository.AuctionBiddingDAO;
import com.app.gradationback.repository.AuctionDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final AuctionDAO auctionDAO;
    private final AuctionBiddingDAO auctionBiddingDAO;

    @Override
    public void auctionRegistration(AuctionVO auctionVO) {
        auctionDAO.insert(auctionVO);
    }

    @Override
    public List<AuctionDTO> auctionList(HashMap<String, Object> params) {
        return auctionDAO.selectAll(params);
    }

    @Override
    public List<AuctionDTO> auctionRead(Long id) {
        return auctionDAO.selectById(id);
    }

    @Override
    public void auctionModify(AuctionVO auctionVO) {
        auctionDAO.update(auctionVO);
    }

    @Override
    public void auctionDelete(Long id) {
        auctionDAO.delete(id);
    }


    @Override
    public void auctionBidding(AuctionBiddingVO auctionBiddingVO) {
//        현재 최상위 자동응찰 가져오기
        AuctionBiddingVO topAutoBidding = auctionBiddingDAO.selectAuto(auctionBiddingVO.getAuctionId()).orElse(null);
//        최상위 일반 응찰
        AuctionBiddingVO topBidding = auctionBiddingDAO.select(auctionBiddingVO.getAuctionId()).orElse(null);

//        인풋 응찰 등록
        auctionBiddingDAO.insert(auctionBiddingVO);
        int biddingPrice = auctionBiddingVO.getAuctionBiddingPrice();
        int minPrice =  (int)Math.ceil(biddingPrice * 1.1 / 1000) * 1000;

//        0으로 할 경우 오류 방지
        int autoMinPrice = 1;
        if (topAutoBidding != null) {
            autoMinPrice =  (int)Math.ceil(topAutoBidding.getAuctionBiddingPrice() * 1.1 / 1000) * 1000;
        }

//        일반 응찰
        if(!auctionBiddingVO.isAuctionBiddingAutoOk()) {
//        일반 응찰인 경우 => 최상위 자동 응찰과 비교 후 경매 갱신 => 자동응찰의 응찰
            if(topAutoBidding != null && topAutoBidding.getAuctionBiddingPrice() >= minPrice){
                topAutoBidding.setAuctionBiddingAutoOk(false);
                topAutoBidding.setAuctionBiddingPrice(minPrice);
                auctionBiddingDAO.insert(topAutoBidding);
            }
        }else {
//        자동 응찰인 경우 => 최상위 자동 응찰과 비교 후 경매 갱신

            if(topAutoBidding != null && topAutoBidding.getAuctionBiddingPrice() > minPrice && topBidding != null){
//                최상위 자동 응찰의 응찰가가 현재 응찰가의 최소 응찰가 이상일 때
//                현재 응찰가의 최소 응찰가로 최상위 자동응찰이 응찰한다.
                auctionBiddingVO.setAuctionBiddingAutoOk(false);
                auctionBiddingVO.setAuctionBiddingPrice((int)Math.ceil(topBidding.getAuctionBiddingPrice() * 1.1 / 1000) * 1000);

                topAutoBidding.setAuctionBiddingAutoOk(false);
                topAutoBidding.setAuctionBiddingPrice(minPrice);
                auctionBiddingDAO.insert(topAutoBidding);

            }else if(topAutoBidding != null && autoMinPrice < biddingPrice){
//                최상위 자동응찰가의 최소응찰가 보다 높은 가격으로 자동응찰 한 경우, 최상위 자동응찰의 최소응찰가로
//                현재 응찰된 자동응찰이 응찰한다. 또한 해당 응찰이 최상위 자동응찰로 자동변경된다.
                auctionBiddingVO.setAuctionBiddingAutoOk(false);
                auctionBiddingVO.setAuctionBiddingPrice(autoMinPrice);
                auctionBiddingDAO.insert(auctionBiddingVO);
            }else if (topAutoBidding == null && topBidding != null) {
//                최상위 자동응찰이 없는 경우
                auctionBiddingVO.setAuctionBiddingAutoOk(false);
                auctionBiddingVO.setAuctionBiddingPrice((int)Math.ceil(topBidding.getAuctionBiddingPrice() * 1.1 / 1000) * 1000);

            }
//            두 자동응찰이 서로의 최소 응찰가를 동시에 만족하지 못할경우 더 높은 응찰금의 자동응찰이 응찰 우선권을 가진다.
//            이는 해당 응찰이 최상위 자동응찰이 되므로 만족된다.


        }

    }

    @Override
    public void endBidding(AuctionVO auctionVO) {
        auctionDAO.update(auctionVO);
    }

    @Override
    public Optional<AuctionBiddingVO> auctionStatus(Long auctionId) {
        return auctionBiddingDAO.select(auctionId);
    }

    @Override
    public Optional<Integer> auctionBidderCount(Long auctionId) {
        return auctionBiddingDAO.selectCount(auctionId);
    }


}
