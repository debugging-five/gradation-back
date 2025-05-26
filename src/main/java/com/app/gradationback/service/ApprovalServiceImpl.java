package com.app.gradationback.service;

import com.app.gradationback.domain.*;
import com.app.gradationback.repository.ApprovalDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalDAO approvalDAO;

    @Override
//    리스트 객체 타입은 4개 중 하나가 들어오므로 와일드카드로, 스트링 타입으로 항목 식별(upcycling, art 등)
//
    public List<?> getPendingList(String type) {
        return switch (type.toLowerCase()){
            case "upcycling" -> approvalDAO.findAllUpcyclingPending();
            case "art", "display" -> approvalDAO.findAllArtPending();
            case "writer" -> approvalDAO.findAllWriterPending();
            case "university" -> approvalDAO.findAllUniversityPending();
            case "exhibition" -> approvalDAO.findAllUniversityExhibitionPending();
//            방어코드, IllegalArgumentException > 매개변수 잘못 들어왔을 때!
            default -> throw new IllegalArgumentException("지원하지 않는 타입");
        };
    }

    @Override
    public Optional<?> getPendingById(String type, Long id) {
        return switch (type.toLowerCase()){
            case "upcycling" -> approvalDAO.findUpcyclingPendingById(id);
            case "art", "display" -> approvalDAO.findArtPendingById(id);
            case "writer" -> approvalDAO.findWriterPendingById(id);
            case "university" -> approvalDAO.findUniversityPendingById(id);
            case "exhibition" -> approvalDAO.findUniversityExhibitionPendingById(id);
            default -> throw new RuntimeException("지원하지 않는 타입");
        };
    }

    @Override
//  dto는 UpcyclingDTO, ArtDTO, UserVO(작가용), UserVO(대학교용) > 오브젝트로 받음
    public void updateStatus(String type, Object dto) {
        switch (type.toLowerCase()){
//          오브젝트 DTO로 형변환(다운캐스팅)
            case "upcycling" -> approvalDAO.updateUpcyclingStatus((UpcyclingDTO) dto);
            case "art", "display" -> approvalDAO.updateArtStatus((ArtDTO) dto);
            case "writer" -> approvalDAO.updateWriterStatus((UserVO) dto );
            case "university" -> approvalDAO.updateUniversityStatus((UserVO) dto );
            case "exhibition" -> approvalDAO.updateUniversityExhibitionStatus((UniversityExhibitionDTO) dto);
            default -> throw new RuntimeException("지원하지 않는 타입");
        }

    }
}
