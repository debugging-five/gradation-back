package com.app.gradationback.util;

import lombok.Data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Data
public class CardCompanyMapUtil {
    public static Map<String, String> createMap() {
        Map<String, String> map = new HashMap<>();
        map.put("11", "국민카드");
        map.put("21", "하나카드");
        map.put("31", "BC카드");
        map.put("3K", "기업BC카드");
        map.put("41", "신한카드");
        map.put("51", "삼성카드");
        map.put("61", "현대카드");
        map.put("71", "롯데카드");
        map.put("91", "NH농협카드");
        map.put("36", "씨티카드");
        map.put("33", "우리BC카드");
        map.put("W1", "우리카드");
        map.put("15", "카카오뱅크카드");
        map.put("3A", "케이뱅크카드");
        map.put("24", "토스뱅크카드");
        map.put("46", "광주은행카드");
        map.put("35", "전북은행카드");
        map.put("42", "제주은행카드");
        map.put("30", "한국산업은행카드");
        map.put("34", "Sh수협은행카드");
        map.put("62", "신협카드");
        map.put("38", "새마을금고카드");
        map.put("37", "우체국예금보험카드");
        map.put("39", "저축은행중앙회카드");
        return Collections.unmodifiableMap(map); // 불변 Map으로 만듦
    }
}
