package com.app.gradationback.service;

import java.util.List;
import java.util.Optional;

public interface ApprovalService {
//  리스트 객체 타입 4개 중 하나라 와일드카드로, 스트링 타입으로 항목 식별(upcycling, art 등)
    List<?> getPendingList(String type);
//  옵셔널도 4개 중 하나라 와일드 카드 사용
    Optional<?> getPendingById(String type, Long id);
//  dto는 UpcyclingDTO, ArtDTO, UserVO(작가용), UserVO(대학교용) > 오브젝트로 받음
    public void updateStatus(String type, Object dto);
}
