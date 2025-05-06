package com.app.gradationback.repository;

import com.app.gradationback.domain.DeliveryDTO;
import com.app.gradationback.domain.PaymentCancellationVO;
import com.app.gradationback.domain.PaymentVO;
import com.app.gradationback.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentDAO {

    private final PaymentMapper paymentMapper;

//    주문 등록
    public void save(PaymentVO paymentVO) {
        paymentMapper.insert(paymentVO);
    }

//    주문 취소
    public void delete(PaymentCancellationVO paymentCancellationVO) {
        paymentMapper.insertCancel(paymentCancellationVO);
    }

//    결제 단독 조회
    public Optional<DeliveryDTO> findByAuctionId(Long id) {
        return paymentMapper.select(id);
    }

//    유저 결제 리스트 조회
    public List<DeliveryDTO> findAllByUserId(Long userId) {
        return paymentMapper.selectByUserId(userId);
    }


}
