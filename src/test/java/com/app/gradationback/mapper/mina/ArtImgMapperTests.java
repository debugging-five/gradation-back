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
        artImgVO.setArtImgName("작품 이미지1");
        artImgVO.setArtImgPath("/images/art1");
        artImgVO.setArtId(1L);
        artImgMapper.insert(artImgVO);
        log.info("{}", artImgVO);
    }

//    전체 작품 이미지 조회
    @Test
    public void selectArtImgAllTest() {
        List<ArtImgVO> artImgVOList = artImgMapper.selectAll();
        for (ArtImgVO artImgVO : artImgVOList) {
            log.info("{}", artImgVO);
        }
    }

//    단일 작품 이미지 조회
    @Test
    public void selectArtImgTest() {
        ArtImgVO artImgVO = new ArtImgVO();
        artImgVO.setId(381L);
        artImgMapper.select(artImgVO.getId()).map(ArtImgVO::toString).ifPresent(log::info);
    }

//    작품 이미지 수정
    @Test
    public void updateArtImgTest() {
        ArtImgVO artImgVO = new ArtImgVO();
        artImgVO.setId(381L);
        artImgVO.setArtImgName("수정된 작품 이미지1");
        artImgVO.setArtImgPath("/images/art1");
        artImgMapper.update(artImgVO);
        log.info("{}", artImgVO);
    }

//    작품 이미지 삭제
    @Test
    public void deleteArtImgTest() {
        artImgMapper.delete(381L);
    }
}
