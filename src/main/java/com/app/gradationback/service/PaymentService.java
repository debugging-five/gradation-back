package com.app.gradationback.service;

import com.app.gradationback.domain.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PaymentService {

//    결제
    public Optional<DeliveryDTO> payment(Map<String, Object> paymentData);

//    결제 취소
    public void paymentCancel(PaymentCancellationVO paymentCancellationVO);

//    결제 조회
    public Optional<DeliveryDTO> getPaymentById(Long id);

//    유저 결제 조회
    public List<DeliveryDTO> getPaymentByUserId(Long userId);
}
