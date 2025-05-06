package com.app.gradationback.mapper.mina;

import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.service.ArtPostService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@Slf4j
public class ArtPostServiceTests {

    @Autowired
    private ArtPostService artPostService;

    @Test
    public void removeArtPost() {
        artPostService.removeById(35L);
    }



}
