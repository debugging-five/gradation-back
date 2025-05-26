package com.app.gradationback.mapper;

import com.app.gradationback.domain.AuctionDTO;
import com.app.gradationback.domain.AuctionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Mapper
public interface AuctionMapper {
    public void insert(AuctionVO auctionVO);
    public List<AuctionDTO> selectAll(HashMap<String, Object> params);
    public Integer selectCountBidding(HashMap<String, Object> params);
    public Optional<AuctionDTO> select(Long id);
    public List<AuctionDTO> selectBidding(Integer cursor);
    public Long selectByArtId(Long artId);
    public List<AuctionDTO> selectByUserId(Long userId);
    public void update(AuctionVO auctionVO);
    public void delete(Long id);
}
