package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Data
public class PaymentVO {
    private Long id;
    private String paymentMethod;
    private int paymentAmount;
    private Timestamp paymentDate;
    private String paymentCode;
    private Long auctionId;
}
