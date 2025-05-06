package com.app.gradationback.mapper.duckjun.mapper;

import com.app.gradationback.domain.ArtDTO;
import com.app.gradationback.mapper.ArtMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class ArtMapperTests {

    @Autowired
    private ArtMapper artMapper;

    // 승인 대기 전체 조회 테스트
    @Test
    public void selectAllPendingTest() {
        List<ArtDTO> pendingList = artMapper.selectAllPending();
        pendingList.forEach(art -> log.info("대기 작품: {}", art));
    }

    // 단일 승인 대기 작품 조회 테스트
    @Test
    public void selectPendingByIdTest() {
        Long testId = 24L;
        Optional<ArtDTO> foundArt = artMapper.selectPendingById(testId);
        foundArt.ifPresentOrElse(
                art -> log.info("작품 상세: {}", art),
                () -> log.warn("해당 ID의 작품이 존재하지 않습니다.")
        );
    }

    // 승인 상태 업데이트 테스트
    @Test
    public void updateStatusTest() {
        ArtDTO artDTO = new ArtDTO();
        artDTO.setId(24L); // 실제 ID 사용
        artDTO.setArtStatus("승인"); // or "반려"
        artMapper.updateStatus(artDTO);
        log.info("작품 ID {} 상태 변경 완료", artDTO.getId());
    }
}
