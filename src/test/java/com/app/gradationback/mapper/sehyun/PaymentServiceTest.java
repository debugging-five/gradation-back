package com.app.gradationback.mapper.sehyun;

import com.app.gradationback.domain.DeliveryDTO;
import com.app.gradationback.domain.PaymentVO;
import com.app.gradationback.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
@RequiredArgsConstructor
public class PaymentServiceTest {

    @Autowired
    private PaymentMapper paymentMapper;

    @Test
    public void insertTest() {
        PaymentVO paymentVO = new PaymentVO();
        paymentVO.setAuctionId(4L);
        paymentVO.setPaymentMethod("oo은행");
        paymentVO.setPaymentAmount(250000);
        paymentVO.setPaymentCode("00000003");
        log.info("-------------------");
        log.info("paymentVO:{}", paymentVO);
        paymentMapper.insert(paymentVO);
        log.info("paymentVO:{}", paymentVO);
        log.info("-------------------");
    }

    @Test
    public void selectTest() {
        Optional<DeliveryDTO> foundPayment = paymentMapper.select(21L);

        if(foundPayment.isPresent()) {
            log.info("{}",foundPayment.get());
        }else {
            log.info("조회 실패");
        }
    }

    @Test
    public void selectByUserIdTest() {
        List<DeliveryDTO> foundPayment = paymentMapper.selectByUserId(3L);
        for(DeliveryDTO deliveryDTO : foundPayment) {
            log.info("{}",deliveryDTO);
        }
    }

}
