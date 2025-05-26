package com.app.gradationback.mapper.mina;

import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.mapper.ArtPostMapper;
import com.app.gradationback.repository.ArtImgDAO;
import com.app.gradationback.repository.CommentDAO;
import com.app.gradationback.service.ArtPostService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
public class ArtPostServiceTests {

    @Autowired
    private ArtPostService artPostService;

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private ArtImgDAO artImgDAO;

    @Test
    public void removeArtPost() {
        artPostService.removeById(35L);
    }

    @Test
    public void commentDAOTest() {
//        log.info("comments :{}", commentDAO.findAllByPostId(1L));
    }

    @Test
    public void artImgDAOTest(){
        log.info("images :{}", artImgDAO.findAllByArtId(220L));
    }


    @Test
    public void getArtPostTest() {
        Map<String, Object> params = new HashMap<>();
        params.put("order", 1);
        params.put("cursor", 1);
        params.put("category", "한국화");
        params.put("keyword", "");
        log.info("{}", artPostService.getArtListByCategoryAndDropdown(params));
    }



}
