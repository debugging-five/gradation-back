package com.app.gradationback.mapper.mina;

import com.app.gradationback.domain.ArtImgVO;
import com.app.gradationback.mapper.ArtImgMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class ArtImgMapperTests {

    @Autowired
    private ArtImgMapper artImgMapper;

//    작품 이미지 등록
    @Test
    public void insertArtImgTest() {
        ArtImgVO artImgVO = new ArtImgVO();
        artImgVO.setArtImgName("이미지이름");
        artImgVO.setArtImgPath("이미지경로");
        artImgVO.setArtId(438L);
        artImgMapper.insert(artImgVO);
        log.info("{}", artImgVO);
    }

//    작품 이미지 삭제
    @Test
    public void deleteArtImgTest() {
        artImgMapper.deleteAllByArtId(383L);
    }
}
