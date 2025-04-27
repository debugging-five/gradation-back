package com.app.gradationback.service;

import com.app.gradationback.domain.UserVO;

import java.util.List;
import java.util.Optional;

public interface UserService {

//    회원가입
    public void join(UserVO userVO);

//    전체 회원 정보 조회
    public List<UserVO> getUserList();

//    단일 회원 정보 조회
    public Optional<UserVO> getUser(String userEmail);

//    로그인
    public String login(UserVO userVO);

//    아이디 중복 체크
    public int isIdExist(String userIdentification);

//    이메일 중복 체크
    public int isEmailExist(String userEmail);

//    비밀번호 중복 체크
    public int isPasswordExist(UserVO userVO);

//    아이디 조회
    public String getIdByEmailAndName(UserVO userVO);

//    비밀번호 조회
    public String getPasswordByEmail(String userEmail);

//    이메일 조회
    public String getEmailById(Long id);

//    회원 정보 수정
    public void modifyUser(UserVO userVO);

//    비밀번호 변경
    public void modifyPassword(UserVO userVO);

//    회원 탈퇴
    public void withdraw(String userEmail);

    public Long getIdByEmail(String userEmail);
}
