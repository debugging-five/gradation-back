package com.app.gradationback.service;

import com.app.gradationback.domain.DeliveryVO;

import java.util.Optional;

public interface DeliveryService {

    public Optional<DeliveryVO> deliveryRead(Long id);

    public void deliveryUpdate(DeliveryVO deliveryVO);
}
