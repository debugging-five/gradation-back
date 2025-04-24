package com.app.gradationback.repository;

import com.app.gradationback.domain.UserVO;
import com.app.gradationback.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDAO {

    private final UserMapper userMapper;


//    회원 등록
    public void register(UserVO userVO) {
        userMapper.insert(userVO);
    }


}
