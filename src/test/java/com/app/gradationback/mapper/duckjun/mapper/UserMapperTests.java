package com.app.gradationback.mapper.duckjun.mapper;

import com.app.gradationback.domain.BanDTO;
import com.app.gradationback.domain.UserVO;
import com.app.gradationback.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsertBan() {
        BanDTO banDTO = new BanDTO();
        banDTO.setBanReason("욕설");
        banDTO.setBanDate(new Timestamp(System.currentTimeMillis()));
        banDTO.setUserId(2L); // TBL_USER에 존재하는 유저 ID

        userMapper.insertBan(banDTO);
        log.info("정지 완료");
    }

}
