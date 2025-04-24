package com.app.gradationback.mapper.duckjun;

import com.app.gradationback.domain.UpcyclingVO;
import com.app.gradationback.mapper.UpcyclingMapper;
import com.app.gradationback.repository.UpcyclingDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class upcyclingMapperTests {
    @Autowired
    private UpcyclingMapper upcyclingMapper;
    @Autowired
    private UpcyclingVO upcyclingVO;
    @Autowired
    private UpcyclingDAO upcyclingDAO;

    @Test
    public void selectAllTest(){log.info("전체 신청 목록 : {}", upcyclingMapper.selectAll());}

    @Test
    public void selectByIdTest(){log.info("단일 ID 조회 : {}", upcyclingMapper.select(1L));}

    @Test
    public void insertTest(){
        UpcyclingVO upcyclingVO = new UpcyclingVO();
        upcyclingVO.setUpcyclingCategory("금속");
        upcyclingVO.setUpcyclingSize("소형 1");
        upcyclingVO.setUpcyclingDate(new Date());
        upcyclingVO.setUpcyclingAddress("서울시 강남구 테헤란로 131");
        upcyclingVO.setUpcyclingImgName("default.jpg");
        upcyclingVO.setUpcyclingImgPath("assets/images/upcycling");
        upcyclingVO.setUpcyclingSignificant("지속 가능한 재활용");
        upcyclingVO.setUpcyclingStatus("미신청");
        upcyclingVO.setUserId(2L);

        upcyclingMapper.insert(upcyclingVO);
    }

    @Test
    public void findAllTest() {
        List<UpcyclingVO> list = upcyclingDAO.getAll();
        list.forEach(vo -> log.info(vo.toString()));
    }

    @Test
    public void getByIdTest() {
        Optional<UpcyclingVO> found = upcyclingDAO.getById(2L);
        found.ifPresent(vo -> log.info(vo.toString()));
    }

    @Test
    public void updateTest() {
        UpcyclingVO vo = new UpcyclingVO();
        vo.setId(1L); // 실제 존재하는 ID 입력
        vo.setUpcyclingStatus("신청완료");
        upcyclingMapper.update(upcyclingVO);
    }

    @Test
    public void deleteTest() {
        upcyclingMapper.delete(3L); // 삭제할 ID 입력
    }
}


