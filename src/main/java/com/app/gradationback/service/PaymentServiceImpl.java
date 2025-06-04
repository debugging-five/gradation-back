package com.app.gradationback.service;

import com.app.gradationback.domain.DeliveryDTO;
import com.app.gradationback.domain.DeliveryVO;
import com.app.gradationback.domain.PaymentCancellationVO;
import com.app.gradationback.domain.PaymentVO;
import com.app.gradationback.exception.PaymentException;
import com.app.gradationback.repository.DeliveryDAO;
import com.app.gradationback.repository.PaymentDAO;
import com.app.gradationback.util.CardCompanyMapUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class PaymentServiceImpl implements PaymentService {

    private final PaymentDAO paymentDAO;
    private final DeliveryDAO deliveryDAO;

    @Value("${toss.payments.api.key}")
    private String apiKey;

    @Value("${toss.payments.api.url}")
    private String apiUrl;

    @Override
    public Optional<DeliveryDTO> payment(Map<String, Object> paymentData) {
        Map<String, String> cardCompanyMap = CardCompanyMapUtil.createMap();

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
        DeliveryVO deliveryVO = new DeliveryVO();
        try {
            JsonNode responseNode = objectMapper.readTree(jsonString);
            if(responseNode.hasNonNull("card")) {
                paymentVO.setPaymentMethod(cardCompanyMap.get(responseNode.path("card").path("issuerCode").asText()));
                paymentVO.setPaymentAmount(responseNode.path("card").path("amount").asLong());
                paymentVO.setPaymentCode(responseNode.path("orderId").asText());
                paymentVO.setAuctionId(auctionId);
                paymentDAO.save(paymentVO);
            }else {
                paymentVO.setPaymentMethod(responseNode.path("easyPay").path("provider").asText());
                paymentVO.setPaymentAmount(responseNode.path("easyPay").path("amount").asLong());
                paymentVO.setPaymentCode(responseNode.path("orderId").asText());
                paymentVO.setAuctionId(auctionId);
                paymentDAO.save(paymentVO);
            }

            deliveryVO.setPaymentId(paymentVO.getId());
            deliveryVO.setDeliveryAddress(paymentData.get("deliveryAddress").toString());
            deliveryVO.setDeliveryDetailAddress(paymentData.get("deliveryDetailAddress").toString());
            deliveryVO.setDeliveryPostalCode(paymentData.get("deliveryPostalCode").toString());
            deliveryVO.setDeliveryMessage(paymentData.get("deliveryMessage").toString());
            deliveryVO.setDeliveryReceiver(paymentData.get("deliveryReceiver").toString());
            deliveryVO.setDeliveryPhone(paymentData.get("deliveryPhone").toString());

            deliveryDAO.save(deliveryVO);

            return paymentDAO.findById(paymentVO.getId());

        } catch (JsonProcessingException e) {
            throw new PaymentException("결제 실패");
        }
    }

    @Override
    public void paymentCancel(PaymentCancellationVO paymentCancellationVO) {
        paymentDAO.delete(paymentCancellationVO);
    }

    @Override
    public Optional<DeliveryDTO> getPaymentById(Long id) {
        return paymentDAO.findById(id);
    }

    @Override
    public Optional<DeliveryDTO> getPaymentByAuctionId(Long auctionId) {
        return paymentDAO.findByAuctionId(auctionId);
    }

    @Override
    public List<DeliveryDTO> getPaymentByUserId(Long userId) {
        return paymentDAO.findAllByUserId(userId);
    }
}
