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

    public void insert(AuctionVO auctionVO) {
        auctionMapper.insert(auctionVO);
    }
    public List<AuctionDTO> selectAll(HashMap<String, Object> params) {
        return auctionMapper.selectAll(params);
    }
    public List<AuctionDTO> selectById(Long id) {
        return auctionMapper.select(id);
    }
    public void update(AuctionVO auctionVO) {
        auctionMapper.update(auctionVO);
    }
    public void delete(Long id) {
        auctionMapper.delete(id);
    }
}
