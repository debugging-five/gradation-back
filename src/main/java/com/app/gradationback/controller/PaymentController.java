package com.app.gradationback.controller;

import com.app.gradationback.domain.DeliveryDTO;
import com.app.gradationback.domain.PaymentCancellationVO;
import com.app.gradationback.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/payments/api/*")
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "결제", description = "결제 API")
    @ApiResponse(responseCode = "200", description = "결제 성공")
    @PostMapping("/payment")
    public ResponseEntity<String> processPayment(@RequestBody Map<String, Object> paymentData) {
//        log.info("processPayment: " + paymentData);
        String response = paymentService.payment(paymentData);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "결제취소", description = "결제 취소 API")
    @ApiResponse(responseCode = "200", description = "결제 취소 성공")
    @PostMapping("/cancel")
    public void processPayment(@RequestBody PaymentCancellationVO paymentCancellationVO) {
        paymentService.paymentCancel(paymentCancellationVO);
    }

    @Operation(summary = "결제 조회", description = "결제 조회 API")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @Parameter(
            name = "id",
            description = "결제 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("/payment/{id}")
    public DeliveryDTO getPaymentById(@PathVariable Long id) {
        Optional<DeliveryDTO> deliveryDTO = paymentService.getPaymentById(id);
        if (deliveryDTO.isPresent()) {
            return deliveryDTO.get();
        }
        return new DeliveryDTO();
    }

    @Operation(summary = "결제 전체 조회", description = "결제 전체 조회 API")
    @ApiResponse(responseCode = "200", description = "조회 성공")
    @Parameter(
            name = "userId",
            description = "결제 유저 번호",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("/payment/all/{userId}")
    public List<DeliveryDTO> getPaymentByUserId(@PathVariable Long userId) {
        List<DeliveryDTO> deliveryDTOList = paymentService.getPaymentByUserId(userId);
        if (deliveryDTOList.isEmpty()) {
            return new ArrayList<>();
        }
        return deliveryDTOList;
    }

}
