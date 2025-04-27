package com.app.gradationback.mapper;

import com.app.gradationback.domain.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

//    회원가입
    public void insert(UserVO userVO);

//    전체 회원 정보 조회
    public List<UserVO> selectAllUser();

//    단일 회원 정보 조회
    public Optional<UserVO> selectUser(String userEmail);

//    로그인
    public String login(UserVO userVO);

//    아이디 중복 체크
    public int checkId(String userIdentification);

//    이메일 중복 체크
    public int checkEmail(String userEmail);

//    비밀번호 중복 체크
    public int checkPassword(UserVO userVO);

//    아이디 조회
    public String selectIdByEmailAndName(UserVO userVO);

//    비밀번호 조회
    public String selectPasswordByEmail(String userEmail);

//    이메일 조회
    public String selectEmailById(Long id);

//    회원 정보 수정
    public void updateUser(UserVO userVO);

//    비밀번호 변경
    public void updatePassword(UserVO userVO);

//    회원 탈퇴
    public void deleteUser(String userEmail);
}
