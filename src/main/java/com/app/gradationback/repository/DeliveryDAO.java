package com.app.gradationback.repository;

import com.app.gradationback.domain.DeliveryVO;
import com.app.gradationback.mapper.DeliveryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DeliveryDAO {
    private final DeliveryMapper deliveryMapper;

    public void save(DeliveryVO deliveryVO) {
        deliveryMapper.insert(deliveryVO);
    }

    public Optional<DeliveryVO> findById(Long id) {
        return deliveryMapper.select(id);
    }

    public void update(DeliveryVO deliveryVO) {
        deliveryMapper.update(deliveryVO);
    }
}
