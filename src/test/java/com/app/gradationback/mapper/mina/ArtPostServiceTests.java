package com.app.gradationback.mapper.mina;

import com.app.gradationback.service.ArtPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ArtPostServiceTests {

    @Autowired
    private ArtPostService artPostService;

//    @Autowired
//    private DisplayService displayService;

//    @Test
//    public void registerPostTest() {
//    ArtPostDTO artPostDTO = new ArtPostDTO();
//    artPostDTO.setArtTitle("new 작품3");
//    artPostDTO.setArtCategory("new 카테고리3");
//    artPostDTO.setArtMaterial("new 재료3");
//    artPostDTO.setArtSize("500 x 500 x 500");
//    artPostDTO.setArtDescription("new 설명3");
//    artPostDTO.setUserId(3L);
//    try {
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        Date endDate = formatter.parse("2025-05-04");
//        artPostDTO.setArtEndDate(endDate);
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//
//    artPostDTO.setArtImgName("new-art3.img");
//    artPostDTO.setArtImgPath("images/new-art3.img");
//
//    artPostDTO.setArtPostDate(new Timestamp(System.currentTimeMillis()));
//
//    displayService.registerDisplay(artPostDTO);
//}


//    게시글 등록
//    @Test
//    public void registerPostTest() {
//        ArtPostDTO artPostDTO = new ArtPostDTO();
//        artPostDTO.setArtTitle("new 작품2");
//        artPostDTO.setArtCategory("new 카테고리2");
//        artPostDTO.setArtMaterial("new 재료2");
//        artPostDTO.setArtSize("500 x 500 x 500");
//        artPostDTO.setArtDescription("new 설명2");
//        artPostDTO.setUserId(2L);
//        try {
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            Date endDate = formatter.parse("2025-05-04");
//            artPostDTO.setArtEndDate(endDate);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        artPostDTO.setArtImgName("new-art2.img");
//        artPostDTO.setArtImgPath("images/new-art2.img");
//
//        artPostDTO.setArtPostDate(new Timestamp(System.currentTimeMillis()));
//
//        artPostService.register(artPostDTO);
//    }


//    게시글 삭제
//    @Test
//    public void removeAllPostTest() {
//        artPostService.removeAllByUserId(69L);
//    }


}
