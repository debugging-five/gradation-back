package com.app.gradationback.service;

import com.app.gradationback.domain.UserVO;

import java.util.List;
import java.util.Optional;

public interface UserService {

//    회원가입
    public void join(UserVO userVO);

//    전체 회원 조회
    public List<UserVO> getUserList();

//    단일 회원 조회 (이메일로)
    public Optional<UserVO> getUserByEmail(String userEmail);

//    단일 회원 조회 (아이디로)
    public Optional<UserVO> getUserByIdentification(String userIdentification);

//    로그인
    public String login(UserVO userVO);

//    아이디 찾기 (이름 + 이메일)
    public String getIdentificationByEmailAndName(UserVO userVO);

//    비밀번호 찾기 (이메일)
    public String getPasswordByEmail(String userEmail);

//    아이디로 이메일 조회
    public String getEmailById(Long id);

//    이메일로 ID 조회
    public Long getIdByEmail(String userEmail);

//    회원 정보 수정
    public void modifyUser(UserVO userVO);

//    회원 탈퇴
    public void withdraw(String userEmail);
}
