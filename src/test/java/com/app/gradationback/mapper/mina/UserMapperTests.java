package com.app.gradationback.mapper.mina;

import com.app.gradationback.domain.UserVO;
import com.app.gradationback.mapper.UserMapper;
import com.app.gradationback.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserVO userVO;
    @Autowired
    private UserService userService;

    //    회원 가입
    @Test
    public void insertUserTest() {
        UserVO userVO = new UserVO();
        userVO.setUserName("유저3");
        userVO.setUserEmail("user3@gmail.com");
        userVO.setUserIdentification("아이디3");
        userVO.setUserPassword("1234");
        userVO.setUserPhone("01033331234");
        userMapper.insert(userVO);
        log.info("{}", userVO);
    }

//    전체 회원 정보 조회
    @Test
    public void selectAllUserTest() {
        List<UserVO> userList = userMapper.selectAllUser();
        for (UserVO userVO : userList) {
            log.info("{}", userVO);
        }
    }

//    단일 회원 정보 조회
//    @Test
//    public void selectUserTest() {
//        UserVO userVO = new UserVO();
//        userVO.setUserEmail("user1@gmail.com");
//        userMapper.selectUser(userVO.getUserEmail()).map(UserVO::toString).ifPresent(log::info);
//    }

//    회원 정보 수정
    @Test
    public void updateUserTest() {
        UserVO userVO = new UserVO();
        userVO.setUserIdentification("아이디1");
        userVO.setUserName("회원 이름 수정!");
        userVO.setUserPhone("01012341234");
        userVO.setUserEmail("user1234@gmail.com");
        userMapper.updateUser(userVO);
        log.info("{}", userVO);
    }

//    비밀번호 조회
    @Test
    public void selectPasswordTest() {
        UserVO userVO = new UserVO();
        userVO.setUserEmail("user1234@gmail.com");
        String password = userMapper.selectPasswordByEmail(userVO.getUserEmail());
        log.info("{}", password);
    }

//    이메일 찾기
    @Test
    public void selectEmailTest() {
        UserVO userVO = new UserVO();
//        userVO.setId(56L);
        userVO.setId(1L);
        String Email = userMapper.selectEmailById(userVO.getId());
        log.info("{}", Email);
    }

//    회원 탈퇴
    @Test
    public void deleteUserTest() {
        userMapper.deleteUser("user1234@gmail.com");
    }

    @Test
    public void withdrawUser() {
        UserVO userVO = new UserVO();
        userVO.setUserEmail("user2@gmail.com");
        userService.withdraw(userVO.getUserEmail());
    }


}
