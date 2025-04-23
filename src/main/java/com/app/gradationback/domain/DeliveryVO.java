package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class DeliveryVO {
    private Long id;
    private String deliveryAddress;
    private String deliveryDetailAddress;
    private String deliveryPostalCode;
    private String deliveryState;
    private String deliveryMessage;
    private String deliveryReceiver;
    private String deliveryPhone;
    private Long paymentId;
}
