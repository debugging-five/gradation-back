package com.app.gradationback.mapper.donggeon;

import com.app.gradationback.domain.*;
import com.app.gradationback.service.ArtistService;
import com.app.gradationback.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class ArtistTests {

    @Autowired
    private ArtistService artistService;

    @Autowired
    UserService userService;

/*    @Test
    public void getMyArtistProfileTest() {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", 2L);

//        Optional<ArtistDTO> myArtist = artistService.getMyArtistProfile(param);

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
    }*/

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

    @Test
    public void getArtistDetailTest() {
        Long userId = 2L;
        ArtistDetailDTO artistDetail = artistService.getArtistDetailById(userId);

        log.info("프로필: {} / {}", artistDetail.getUserImgPath(), artistDetail.getUserImgName());
        log.info("작가 이름: {}", artistDetail.getUserName());
        log.info("대학교: {}", artistDetail.getUniversityName());
        log.info("작가 소개: {}", artistDetail.getUserIntroduce());
        log.info("작품 분야: {}", artistDetail.getUserArtCategory());
        log.info("인스타: {}", artistDetail.getUserInstagram());
        log.info("유튜브: {}", artistDetail.getUserYoutube());
        log.info("블로그: {}", artistDetail.getUserBlog());

        List<HistoryVO> historyList = artistDetail.getHistoryList();
        for (HistoryVO history : historyList) {
            log.info("{} / {}", history.getHistoryDate(), history.getHistoryContent());
        }

        List<ArtImgVO> artImgList = artistDetail.getArtImgList();
        for (ArtImgVO artImg : artImgList) {
            log.info("{} / {}", artImg.getArtImgName(), artImg.getArtImgPath());
        }
    }

    @Test
    public void updateArtistTest() {
        UserVO userVO = new UserVO();
        userVO.setId(2L);
        userVO.setUserIntroduce("취업하고싶은 작가입니다.");
        userVO.setUserArtCategory("건축");
        userVO.setUserInstagram("d_invierno_");

        artistService.editArtist(userVO);
        log.info(userVO.toString());
    }

    @Test
    public void insertArtistHistoryTest() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        HistoryVO historyVO = new HistoryVO();
        historyVO.setUserId(2L);
        historyVO.setHistoryDate(sdf.parse("2025-10-08"));
        historyVO.setHistoryContent("작가 이력 추가 테스트");

        artistService.registerUserHistory(historyVO);
    }

    @Test
    public void deleteArtistHistoryTest() {
        Long historyId = 361L;
        artistService.removeUserHistory(historyId);
    }

    @Test
    public void getUserEmail(){
        log.info("user {}", userService.getUserByEmail("codefuling@gmail.com"));
    }


}

















