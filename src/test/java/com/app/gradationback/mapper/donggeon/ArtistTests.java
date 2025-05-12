package com.app.gradationback.mapper.donggeon;

import com.app.gradationback.domain.ArtistDTO;
import com.app.gradationback.service.ArtistService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class ArtistTests {

    @Autowired
    private ArtistService artistService;

    @Test
    public void getMyArtistProfileTest() {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", 2L);

        Optional<ArtistDTO> myArtist = artistService.getMyArtistProfile(param);

        if (myArtist.isPresent()) {
            ArtistDTO artist = myArtist.get();
            log.info("배경 이미지 경로: {}", artist.getUserBackgroundImgPath());
            log.info("배경 이미지: {}", artist.getUserBackgroundImgName());
            log.info("작가 이름: {}", artist.getUserName());
            log.info("대학교: {}", artist.getUniversityName());
            log.info("프로필 이미지 경로: {}", artist.getUserImgPath());
            log.info("프로필 이미지: {}", artist.getUserImgName());
        } else {
            log.info("작가가 아닙니다.");
        }
    }

    @Test
    public void getArtistList() {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", 2L);
        param.put("category", "서예");
        param.put("order", "name");
        param.put("direction", "asc");
        param.put("cursor", 1);

        List<ArtistDTO> artistList = artistService.getArtistList(param);
        log.info("작가 수:{}", artistList.size());

        for (ArtistDTO artist : artistList) {
            log.info("===========");
            log.info("작가 ID: {}", artist.getId());
            log.info("작가 이름: {}", artist.getUserName());
            log.info("대학교: {}", artist.getUniversityName());
            log.info("프로필 이미지: {}/{}", artist.getUserImgPath(), artist.getUserImgName());
            log.info("배경 이미지: {}/{}", artist.getUserBackgroundImgPath(), artist.getUserBackgroundImgName());
            log.info("분야: {}", artist.getUserArtCategory());
        }

    }


}
