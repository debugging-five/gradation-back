package com.app.gradationback.service;

import com.app.gradationback.domain.DeliveryDTO;
import com.app.gradationback.domain.DeliveryVO;
import com.app.gradationback.domain.PaymentCancellationVO;
import com.app.gradationback.domain.PaymentVO;
import com.app.gradationback.repository.DeliveryDAO;
import com.app.gradationback.repository.PaymentDAO;
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
        Map<String, String> cardCompanyMap = new HashMap<>();
        cardCompanyMap.put("11", "국민카드");
        cardCompanyMap.put("21", "하나카드");
        cardCompanyMap.put("31", "BC카드");
        cardCompanyMap.put("3K", "기업BC카드");
        cardCompanyMap.put("41", "신한카드");
        cardCompanyMap.put("51", "삼성카드");
        cardCompanyMap.put("61", "현대카드");
        cardCompanyMap.put("71", "롯데카드");
        cardCompanyMap.put("91", "NH농협카드");
        cardCompanyMap.put("36", "씨티카드");
        cardCompanyMap.put("33", "우리BC카드");
        cardCompanyMap.put("W1", "우리카드"); // 응답 전용 코드
        cardCompanyMap.put("15", "카카오뱅크카드");
        cardCompanyMap.put("3A", "케이뱅크카드");
        cardCompanyMap.put("24", "토스뱅크카드");
        cardCompanyMap.put("46", "광주은행카드");
        cardCompanyMap.put("35", "전북은행카드");
        cardCompanyMap.put("42", "제주은행카드");
        cardCompanyMap.put("30", "한국산업은행카드");
        cardCompanyMap.put("34", "Sh수협은행카드");
        cardCompanyMap.put("62", "신협카드");
        cardCompanyMap.put("38", "새마을금고카드");
        cardCompanyMap.put("37", "우체국예금보험카드");
        cardCompanyMap.put("39", "저축은행중앙회카드");

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
                paymentVO.setPaymentAmount(responseNode.path("card").path("amount").asInt());
                paymentVO.setPaymentCode(responseNode.path("orderId").asText());
                paymentVO.setAuctionId(auctionId);
                paymentDAO.save(paymentVO);
            }else {
                paymentVO.setPaymentMethod(responseNode.path("easyPay").path("provider").asText());
                paymentVO.setPaymentAmount(responseNode.path("easyPay").path("amount").asInt());
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
            log.error(e.getMessage());
        }

        return Optional.empty();

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
