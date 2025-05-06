package com.app.gradationback.service;

import com.app.gradationback.domain.DeliveryDTO;
import com.app.gradationback.domain.PaymentCancellationVO;
import com.app.gradationback.domain.PaymentVO;
import com.app.gradationback.repository.PaymentDAO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class PaymentServiceImpl implements PaymentService {

    private final PaymentDAO paymentDAO;

    @Value("${toss.payments.api.key}")
    private String apiKey;

    @Value("${toss.payments.api.url}")
    private String apiUrl;

    @Override
    public String payment(Map<String, Object> paymentData) {

        RestTemplate restTemplate = new RestTemplate();
        Long auctionId = Long.valueOf(paymentData.get("auctionId").toString());
//        API키를 Base64로 인코딩
        String encodedApiKey = Base64.getEncoder().encodeToString((apiKey + ":").getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + encodedApiKey);
        headers.set("Content-Type", "application/json; charset=utf-8");
        headers.set("Accept", "application/json; charset=utf-8");

//        HTTP 바디에 결제 데이터 추가
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(paymentData, headers);
        ObjectMapper objectMapper = new ObjectMapper();

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);

        String jsonString = response.getBody();

        PaymentVO paymentVO = new PaymentVO();
        try {
            JsonNode responseNode = objectMapper.readTree(jsonString);
            paymentVO.setPaymentMethod(responseNode.path("easyPay").path("provider").asText());
            paymentVO.setPaymentAmount(responseNode.path("easyPay").path("amount").asInt());
            paymentVO.setPaymentCode(responseNode.path("orderId").asText());
            paymentVO.setAuctionId(auctionId);
            log.info("paymentVO: " + paymentVO);

            paymentDAO.save(paymentVO);
        } catch (JsonProcessingException e) {
            return null;
        }

        return response.toString();
    }

    @Override
    public void paymentCancel(PaymentCancellationVO paymentCancellationVO) {
        paymentDAO.delete(paymentCancellationVO);
    }

    @Override
    public Optional<DeliveryDTO> getPaymentById(Long id) {
        return paymentDAO.findByAuctionId(id);
    }

    @Override
    public List<DeliveryDTO> getPaymentByUserId(Long userId) {
        return paymentDAO.findAllByUserId(userId);
    }
}
