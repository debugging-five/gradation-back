package com.app.gradationback.service;

import com.app.gradationback.domain.AuctionBiddingVO;
import com.app.gradationback.domain.AuctionDTO;
import com.app.gradationback.domain.AuctionPriceVO;
import com.app.gradationback.domain.AuctionVO;
import com.app.gradationback.exception.AuctionException;
import com.app.gradationback.exception.BiddingException;
import com.app.gradationback.repository.ArtImgDAO;
import com.app.gradationback.repository.AuctionBiddingDAO;
import com.app.gradationback.repository.AuctionDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
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
    private final ArtImgDAO artImgDAO;
    private final AuctionBiddingDAO auctionBiddingDAO;

    @Override
    public void auctionRegistration(AuctionVO auctionVO) {
        auctionDAO.save(auctionVO);
    }

    @Override
    public List<AuctionDTO> readAuctionList(HashMap<String, Object> params) {
        return auctionDAO.findAll(params);
    }

    @Override
    public Integer auctionCountList(HashMap<String, Object> params) {
        return auctionDAO.findCountByParams(params);
    }

    @Override
    public Optional<AuctionDTO> auctionRead(Long id) {
        return auctionDAO.findById(id).map((auctionDTO -> {
            auctionDTO.setArgImgList(artImgDAO.findAllByArtId(auctionDTO.getArtId()));
            return auctionDTO;
        }));
    }

    @Override
    public List<AuctionDTO> auctionFooterBidding(Integer cursor) {
        return auctionDAO.findAuctionByCursor(cursor);
    }

    @Override
    public Integer auctionFooterBiddingCount() {
        return auctionDAO.findAuctionCount();
    }

    @Override
    public Long auctionFindByArtId(Long artId) {
        return auctionDAO.findAuctionCountByArtId(artId);
    }

    @Override
    public List<AuctionDTO> auctionFindByUserId(Long userId) {
        return auctionDAO.findAuctionByUserId(userId);
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
    public void auctionBidding(@NotNull AuctionBiddingVO auctionBiddingVO) {
        AuctionBiddingVO topAutoBidding = auctionBiddingDAO.findAutoByAuctionId(auctionBiddingVO.getAuctionId()).orElse(null);
        AuctionBiddingVO topBidding = auctionBiddingDAO.findByAuctionId(auctionBiddingVO.getAuctionId()).orElse(null);
        Long startPrice = auctionDAO.findById(auctionBiddingVO.getAuctionId()).orElseThrow(BiddingException::new).getAuctionStartPrice();

        auctionBiddingDAO.save(auctionBiddingVO);
        Long biddingPrice = auctionBiddingVO.getAuctionBiddingPrice();
        Long minPrice =  (long)Math.ceil(biddingPrice * 1.1 / 1000) * 1000;

        Long autoMinPrice = 1L;
        if (topAutoBidding != null) {
            autoMinPrice =  (long)Math.ceil(topAutoBidding.getAuctionBiddingPrice() * 1.1 / 1000) * 1000;
        }

        if(!auctionBiddingVO.isAuctionBiddingAutoOk()) {
            if(topAutoBidding != null && topAutoBidding.getAuctionBiddingPrice() >= minPrice){
                topAutoBidding.setAuctionBiddingAutoOk(false);
                topAutoBidding.setAuctionBiddingPrice(minPrice);
                auctionBiddingDAO.save(topAutoBidding);
            }
        }else {
            if(topAutoBidding != null && topAutoBidding.getAuctionBiddingPrice() > minPrice && topBidding != null){
                auctionBiddingVO.setAuctionBiddingAutoOk(false);
                auctionBiddingVO.setAuctionBiddingPrice((long)Math.ceil(topBidding.getAuctionBiddingPrice() * 1.1 / 1000) * 1000);

                topAutoBidding.setAuctionBiddingAutoOk(false);
                topAutoBidding.setAuctionBiddingPrice(minPrice);
                auctionBiddingDAO.save(topAutoBidding);

            }else if(topAutoBidding != null && autoMinPrice < biddingPrice){
                auctionBiddingVO.setAuctionBiddingAutoOk(false);
                auctionBiddingVO.setAuctionBiddingPrice(autoMinPrice);
                auctionBiddingDAO.save(auctionBiddingVO);

            }else if (topAutoBidding == null && topBidding != null) {
                auctionBiddingVO.setAuctionBiddingAutoOk(false);
                auctionBiddingVO.setAuctionBiddingPrice((long) Math.ceil(topBidding.getAuctionBiddingPrice() * 1.1 / 1000) * 1000);
                auctionBiddingDAO.save(auctionBiddingVO);
            }else {
                auctionBiddingVO.setAuctionBiddingAutoOk(false);
                auctionBiddingVO.setAuctionBiddingPrice(startPrice);
                auctionBiddingDAO.save(auctionBiddingVO);
            }
        }
    }

    @Override
    public void endBidding(AuctionVO auctionVO) {
        auctionDAO.update(auctionVO);
    }

    @Override
    public Optional<AuctionBiddingVO> auctionStatus(Long auctionId) {
        return auctionBiddingDAO.findByAuctionId(auctionId);
    }

    @Override
    public Optional<Integer> auctionBidderCount(Long auctionId) {
        return auctionBiddingDAO.findCountByAuctionId(auctionId);
    }

    @Override
    public Optional<AuctionPriceVO> getLatestPrice(Long auctionId) {
        return auctionBiddingDAO.findPrice(auctionId);
    }

}
