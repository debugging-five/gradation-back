package com.app.gradationback.mapper.mina;

import com.app.gradationback.domain.ArtPostDTO;
import com.app.gradationback.domain.UserVO;
import com.app.gradationback.mapper.DisplayMapper;
import com.app.gradationback.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserVO userVO;

//    회원 등록
    @Test
    public void insertTest() {
        UserVO userVO = new UserVO();
        userVO.setUserName("유저3");
        userVO.setUserEmail("user000@gmail.com");
        userVO.setUserIdentification("user0000");
        userVO.setUserPassword("123456");
        userVO.setUserPhone("01000000000");
        userVO.setUserNickName("닉네임3");
    }



}
