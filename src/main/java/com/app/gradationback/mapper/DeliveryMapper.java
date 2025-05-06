package com.app.gradationback.mapper;

import com.app.gradationback.domain.AuctionVO;
import com.app.gradationback.domain.DeliveryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Mapper
public interface DeliveryMapper {

    public void insert(DeliveryVO deliveryVO);

    public Optional<DeliveryVO> select(Long id);

    public void update(DeliveryVO deliveryVO);

}
