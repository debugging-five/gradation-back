package com.app.gradationback.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;


@Component
@Data
@Schema(description = "결제 정보")
public class PaymentVO {

    @Schema(description = "결제번호", required = true, example = "1")
    private Long id;

    @Schema(description = "결제수단", required = true, example = "카드결제")
    private String paymentMethod;

    @Schema(description = "결제 금액", required = true, example = "1000000")
    private int paymentAmount;

    @Schema(description = "결제 일시", example = "2025-05-04 11:15:27")
    private String paymentDate;

    @Schema(description = "결제 코드", example = "2170011098")
    private String paymentCode;

    @Schema(description = "결제 코드", example = "1")
    private Long auctionId;
}
