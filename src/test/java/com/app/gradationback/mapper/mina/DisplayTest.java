package com.app.gradationback.mapper.mina;

import com.app.gradationback.domain.ArtImgVO;
import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.domain.ArtVO;
import com.app.gradationback.domain.DisplayDTO;
import com.app.gradationback.mapper.DisplayMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class DisplayTest {

    @Autowired
    private DisplayMapper displayMapper;

    @Autowired
    private ArtPostDTO artPostDTO;

//    전시 등록
    @Test
    public void insertArtTest() {
        ArtVO artVO = new ArtVO();
        artVO.setArtTitle("작품1");
        artVO.setArtCategory("카테고리1");
        artVO.setArtDescription("설명1");
        artVO.setArtSize("100X100X100");
        artVO.setArtMaterial("재료1");
        artVO.setUserId(1L);
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date endDate = formatter.parse("2025-05-01");
            artVO.setArtEndDate(endDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        displayMapper.insertArt(artVO);
        log.info("{}", artVO);
    }

//    전시 이미지 등록
    @Test
    public void insertArtImgTest() {
        ArtImgVO artImgVO = new ArtImgVO();
        artImgVO.setArtImgName("작품 이미지1");
        artImgVO.setArtImgPath("/images/art1");
        artImgVO.setArtId(1L);
        displayMapper.insertArtImg(artImgVO);
        log.info("{}", artImgVO);
    }

//    전시 리스트
    @Test
    public void selectAllTest() {
        List<ArtPostDTO> artList = displayMapper.selectAll();
        log.info("{}", artList);
    }

//    전시 상세
    @Test
    public void selectTest() {
        Long id = 3L;
        Optional<ArtPostDTO> artPostDTO = displayMapper.select(id);
        log.info("{}", artPostDTO);
    }
}
