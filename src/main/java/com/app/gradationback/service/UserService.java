package com.app.gradationback.service;

import com.app.gradationback.domain.BanDTO;
import com.app.gradationback.domain.UserVO;

import java.util.List;
import java.util.Optional;

public interface UserService {

//    일반 회원가입
    public void joinNormal(UserVO userVO);

//    소셜 로그인 후 회원가입
    public void joinSocial(UserVO userVO);

//    전체 회원 조회
    public List<UserVO> getUserList();

//    단일 회원 조회 (이메일로)
    public Optional<UserVO> getUserByEmail(String userEmail);

//    단일 회원 조회 (아이디로)
    public Optional<UserVO> getUserByIdentification(String userIdentification);

//    로그인
    public String login(UserVO userVO);

//    아이디 찾기 (이름 + 이메일)
    public UserVO getIdentificationByEmailAndName(UserVO userVO);

//    비밀번호 찾기 (이메일)
    public String getPasswordByEmail(String userEmail);

//    아이디로 이메일 조회
    public String getEmailById(Long id);

//    이메일로 ID 조회
    public Long getIdByEmail(String userEmail);

//    비밀번호 재설정
    public void modifyPassword(UserVO userVO);

//    회원 정보 수정
    public void modifyUser(UserVO userVO);

//    회원 탈퇴
    public void withdraw(String userEmail);

//    관리자용 유저 정지 처리
    public void banUser(BanDTO banDTO);

    public void updateUserBanStatus(BanDTO banDTO);

//    댓글 작성 시 정지 유저 확인
    public Optional<UserVO> findUserByIdForWrite(Long id);

}
