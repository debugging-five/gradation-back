package com.app.gradationback.mapper.seungmin;

import com.app.gradationback.domain.QnaDTO;
import com.app.gradationback.domain.QnaVO;
import com.app.gradationback.mapper.QnaMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class QnaMapperTests {

    @Autowired
    private QnaMapper qnaMapper;

//    등록
    @Test
    public void insertTest() {
        QnaVO qnaVO = new QnaVO();
        qnaVO.setQnaTitle("경매는 어떻게 하는 건가요??");
        qnaVO.setQnaContent("경매를 시작하기 위해서는 우선 어떡해야 하며 ...");
        qnaVO.setQnaCategory("경매");
        qnaVO.setUserId(2L);
        qnaMapper.insert(qnaVO);
    }

//    모두조회(리스트)
    @Test
    public void selectAllTest() {
        String userEmail = "dong@example.com";
        List<QnaDTO> qnaList = qnaMapper.selectAll(userEmail);
        for (QnaDTO qna : qnaList) {
            log.info("QNA: {}", qna);
        }
    }

//    단일조회
    @Test
    public void selectByIdTest() {
        log.info("{}", qnaMapper.selectById(5L));
    }

    @Test
    public void updateTest() {
        QnaVO qnaVO = new QnaVO();
        qnaVO.setId(4L);
        qnaVO.setQnaTitle("업사이클은 어떻게 하는 건가요??");
        qnaVO.setQnaContent("업사이클 시작하기 위해서는 작품이 있어야 하며 ...");
        qnaVO.setQnaCategory("업사이클");
        qnaMapper.update(qnaVO);
    }

    @Test
    public void deleteTest() {
        qnaMapper.delete(4L);
    }


}
