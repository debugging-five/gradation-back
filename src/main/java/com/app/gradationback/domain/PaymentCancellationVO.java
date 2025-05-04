package com.app.gradationback.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "결제 취소 이력")
public class PaymentCancellationVO {

    @Schema(description = "결제취소번호", required = true, example = "1")
    private Long id;

    @Schema(description = "취소 코드", required = true, example = "400")
    private String paymentCancellationCode;

    @Schema(description = "취소된 결제 번호", required = true, example = "1")
    private Long paymentId;
}
