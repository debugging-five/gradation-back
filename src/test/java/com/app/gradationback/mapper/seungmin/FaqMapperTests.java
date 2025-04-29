package com.app.gradationback.mapper.seungmin;

import com.app.gradationback.domain.FaqDTO;
import com.app.gradationback.domain.FaqVO;
import com.app.gradationback.domain.MailDTO;
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
//    @Test
//    public void insertTest() {
//        FaqDTO faqDTO = new FaqDTO();
//        faqDTO.setFaqTitle("전시관련 주의사항안내");
//        faqDTO.setFaqContent("전시를 진행할때는 일단 어렵게 생각하지 말고...");
//        faqDTO.setFaqCategory("전시");
//        faqDTO.setUserId(1L);
//        faqMapper.insert(faqDTO);
//    }

//    모두조회(리스트)
    @Test
    public void selectAllTest() {
        log.info("{}", faqMapper.selectAll());
    }

//    단일조회
    @Test
    public void selectByIdTest() {
        log.info("{}", faqMapper.selectById(2L));
    }

//    수정
//    @Test
//    public void updateTest() {
//        FaqDTO faqDTO = new FaqDTO();
//        faqDTO.setId(1L);
//        faqDTO.setFaqTitle("전시회는 어떻게 하는 건가요??");
//        faqDTO.setFaqContent("전시회를 시작하기 위해서는 작품이 있어야 하며 ...");
//        faqDTO.setFaqCategory("전시회");
//        faqDTO.setUserId(1L);
//        faqMapper.update(faqDTO);
//    }

    @Test
    public void deleteTest() {
        faqMapper.delete(22L);
    }


}
