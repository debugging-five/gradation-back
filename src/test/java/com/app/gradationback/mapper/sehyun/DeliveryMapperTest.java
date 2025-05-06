package com.app.gradationback.mapper.sehyun;

import com.app.gradationback.domain.DeliveryVO;
import com.app.gradationback.mapper.DeliveryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
@RequiredArgsConstructor
public class DeliveryMapperTest {

    @Autowired
    private DeliveryMapper deliveryMapper;

    @Test
    public void insertTest() {
        DeliveryVO deliveryVO = new DeliveryVO();
        deliveryVO.setDeliveryAddress("서울시 ㅇㅇ구 ㅇㅇ동");
        deliveryVO.setDeliveryDetailAddress("ㅇㅇ아파트 ㅇㅇ동 ㅇㅇ호");
        deliveryVO.setDeliveryPostalCode("00000");
        deliveryVO.setDeliveryMessage("문앞에 놓고 벨");
        deliveryVO.setDeliveryReceiver("홍길동");
        deliveryVO.setDeliveryPhone("010-1234-1234");
        deliveryVO.setPaymentId(22L);
        deliveryMapper.insert(deliveryVO);
    }

    @Test
    public void selectTest() {
        log.info("{}", deliveryMapper.select(1L));
    }

    @Test
    public void updateTest() {
        Optional<DeliveryVO> deliveryVO = deliveryMapper.select(2L);
        deliveryVO.ifPresent(delivery -> {
            delivery.setDeliveryState("배송 완료");
            delivery.setDeliveryReceiver("홍길동");
            delivery.setDeliveryPhone("010-1234-1234");
            deliveryMapper.update(delivery);
            log.info("{}", deliveryMapper.select(23L));
        });

    }

}
