package com.app.gradationback.repository;

import com.app.gradationback.domain.AuctionDTO;
import com.app.gradationback.domain.AuctionVO;
import com.app.gradationback.mapper.AuctionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuctionDAO {
    private final AuctionMapper auctionMapper;

    public void save(AuctionVO auctionVO) {
        auctionMapper.insert(auctionVO);
    }
    public List<AuctionDTO> findAll(HashMap<String, Object> params) {
        return auctionMapper.selectAll(params);
    }
    public List<AuctionDTO> findById(Long id) {
        return auctionMapper.select(id);
    }
    public List<AuctionDTO> findAuctionByCursor(Integer cursor) {
        return auctionMapper.selectBidding(cursor);
    }
    public void update(AuctionVO auctionVO) {
        auctionMapper.update(auctionVO);
    }
    public void delete(Long id) {
        auctionMapper.delete(id);
    }
}
