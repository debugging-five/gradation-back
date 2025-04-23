package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PaymentCancellationVO {
    private Long id;
    private String paymentCancellationCode;
    private Long paymentId;
}
