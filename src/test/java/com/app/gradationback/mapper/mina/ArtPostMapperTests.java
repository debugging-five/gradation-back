//package com.app.gradationback.mapper.mina;
//
//import com.app.gradationback.domain.ArtPostDTO;
//import com.app.gradationback.domain.ArtPostVO;
//import com.app.gradationback.mapper.ArtPostMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//@SpringBootTest
//@Slf4j
//public class ArtPostMapperTests {
//
//    @Autowired
//    private ArtPostMapper artPostMapper;
//
////    작품 게시
//    @Test
//    public void insertArtPostTest() {
//        ArtPostVO artPostVO = new ArtPostVO();
//        artPostVO.setArtPostDate(new Timestamp(System.currentTimeMillis()));
//        artPostVO.setUserId(1L);
//        artPostVO.setArtId(1L);
//        artPostMapper.insert(artPostVO);
//        log.info("{}", artPostVO);
//    }
//
////    작품 게시 조회 (전체)
//    @Test
//    public void selectArtPostAllTest() {
//        List<ArtPostDTO> artPostList = artPostMapper.selectAll();
//        for (ArtPostDTO artPostDTO : artPostList) {
//            log.info("{}", artPostDTO);
//        }
//    }
//
////    작품 게시 조회(단일)
//    @Test
//    public void selectArtPostTest() {
//        ArtPostDTO artPostDTO = new ArtPostDTO();
//        artPostDTO.setId(3L);
//        artPostMapper.select(artPostDTO.getId()).map(ArtPostDTO::toString).ifPresent(log::info);
//    }
//
////    작품 게시 수정
//    @Test
//    public void updateArtPostTest() {
//        ArtPostVO artPostVO = new ArtPostVO();
//        artPostVO.setId(3L);
//        artPostVO.setArtPostDate(new Timestamp(System.currentTimeMillis()));
//        artPostMapper.update(artPostVO);
//        log.info("{}", artPostVO);
//    }
//
////    작품 게시 삭제 (유저 필요)
//    @Test
//    public void deleteArtPostTest() {
//        artPostMapper.delete(21L);
////        artMapper.delete(1L);
//    }
//
////    작품 게시 전체 삭제 (회원 탈퇴)
//
//
//}
