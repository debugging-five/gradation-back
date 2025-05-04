package com.app.gradationback.service;

import com.app.gradationback.domain.DeliveryDTO;
import com.app.gradationback.domain.PaymentCancellationVO;
import com.app.gradationback.repository.PaymentDAO;
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
