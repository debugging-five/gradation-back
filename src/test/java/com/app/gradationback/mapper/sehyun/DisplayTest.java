package com.app.gradationback.mapper.sehyun;

import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.mapper.ArtPostMapper;
import com.app.gradationback.repository.ArtPostDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RequiredArgsConstructor
@Slf4j
public class DisplayTest {

    @Autowired
    private ArtPostMapper artPostMapper;

    @Test
    public void listTest() {
        Map<String, Object> params = new HashMap<>();

        params.put("category", "한국화");
        params.put("cursor", 1);
        params.put("keyword", "");
        params.put("order", "date");
        List<ArtPostDTO> list = artPostMapper.selectArtListByCategoryAndDropdown(params);
        log.info("==============================================");
        log.info(list.toString());
        log.info("==============================================");
    }
}
