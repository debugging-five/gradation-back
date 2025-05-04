package com.app.gradationback.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@Schema(description = "배송 정보")
public class DeliveryVO {

    @Schema(description = "배송 번호", required = true, example = "1")
    private Long id;

    @Schema(description = "주소", required = true, example = "**시 **구 **동")
    private String deliveryAddress;

    @Schema(description = "상세 주소", required = true, example = "**아파트 **동 **호")
    private String deliveryDetailAddress;

    @Schema(description = "우편 번호", required = true, example = "05503")
    private String deliveryPostalCode;

    @Schema(description = "배송 상태", required = true, example = "배송중")
    private String deliveryState;

    @Schema(description = "배송 메세지", required = true, example = "직접 수령하겠습니다")
    private String deliveryMessage;

    @Schema(description = "수취인 이름", required = true, example = "홍길동")
    private String deliveryReceiver;

    @Schema(description = "수취인 전화번호", required = true, example = "010-1234-1234")
    private String deliveryPhone;

    @Schema(description = "결제 번호", required = true, example = "1")
    private Long paymentId;
}
