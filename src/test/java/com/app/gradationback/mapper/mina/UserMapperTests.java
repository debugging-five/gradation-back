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

//    일반 회원가입
    @Test
    public void insertNormalUserTest() {
        UserVO userVO = new UserVO();
        userVO.setUserName("회원임");
        userVO.setUserEmail("test100@gmail.com");
        userVO.setUserIdentification("test100");
        userVO.setUserPassword("test1234!@#");
        userVO.setUserPhone("01012345678");
        userMapper.insertNormal(userVO);
        log.info("{}", userVO);
    }

//    소셜 로그인 후 회원가입
    @Test
    public void insertSocialUserTest() {
        UserVO userVO = new UserVO();
        userVO.setUserName("닉네임없는 회원4");
        userMapper.insertSocial(userVO);
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
        userVO.setUserName("회원임");
        userVO.setUserEmail("test10@gmail.com");
        userVO.setUserIdentification("test10");
        userVO.setUserPassword("test1234!@#");
        userVO.setUserPhone("01012345678");
        userVO.setUserProvider("google");
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
//        UserVO userVO = new UserVO();
//        userVO.setUserEmail("user3@test.app");
//        userService.withdraw(userVO.getUserEmail());
        userService.withdraw("회원이메일");
    }


}
