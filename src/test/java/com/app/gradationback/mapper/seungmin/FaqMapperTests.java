package com.app.gradationback.mapper.seungmin;

import com.app.gradationback.domain.FaqVO;
import com.app.gradationback.mapper.FaqMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class FaqMapperTests {

    @Autowired
    private FaqMapper faqMapper;

//    등록
    @Test
    public void insertTest() {
        FaqVO faqVO = new FaqVO();
        faqVO.setFaqTitle("경매는 어떻게 하는 건가요??");
        faqVO.setFaqContent("경매를 시작하기 위해서는 우선 돈이 있어야 하며 ...");
        faqVO.setFaqCategory("경매");
        faqMapper.insert(faqVO);
    }

//    모두조회(리스트)
    @Test
    public void selectAllTest() {
        log.info("{}", faqMapper.selectAll());
    }

//    단일조회
    @Test
    public void selectByIdTest() {
        log.info("{}", faqMapper.selectById(23L));
    }

    @Test
    public void updateTest() {
        FaqVO faqVO = new FaqVO();
        faqVO.setId(1L);
        faqVO.setFaqTitle("전시회는 어떻게 하는 건가요??");
        faqVO.setFaqContent("전시회를 시작하기 위해서는 작품이 있어야 하며 ...");
        faqVO.setFaqCategory("전시회");
        faqMapper.update(faqVO);
    }

    @Test
    public void deleteTest() {
        faqMapper.delete(22L);
    }


}
