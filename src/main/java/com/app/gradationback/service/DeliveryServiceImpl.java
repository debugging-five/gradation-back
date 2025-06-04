package com.app.gradationback.service;

import com.app.gradationback.domain.DeliveryVO;
import com.app.gradationback.exception.AuctionException;
import com.app.gradationback.exception.DeliveryException;
import com.app.gradationback.repository.DeliveryDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryDAO deliveryDAO;

    @Override
    public Optional<DeliveryVO> deliveryRead(Long id) {
        return deliveryDAO.findById(id);
    }

    @Override
    public void deliveryUpdate(DeliveryVO deliveryVO) {
        deliveryDAO.update(deliveryVO);
    }

}
