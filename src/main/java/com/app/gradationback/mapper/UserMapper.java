package com.app.gradationback.mapper;

import com.app.gradationback.domain.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

//    회원 등록
    public void insert(UserVO userVO);


}
