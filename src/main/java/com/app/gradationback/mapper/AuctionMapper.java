package com.app.gradationback.mapper;

import com.app.gradationback.domain.AuctionDTO;
import com.app.gradationback.domain.AuctionVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuctionMapper {
    public void insert(AuctionVO auctionVO);
    public List<AuctionDTO> selectAll();
    public List<AuctionDTO> select(Long id);
    public void update(AuctionVO auctionVO);
    public void delete(Long id);
}
