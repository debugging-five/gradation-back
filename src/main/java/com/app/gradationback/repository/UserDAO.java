package com.app.gradationback.repository;

import com.app.gradationback.domain.UserVO;
import com.app.gradationback.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDAO {

    private final UserMapper userMapper;

//    회원가입
    public void save(UserVO userVO) {
        userMapper.insert(userVO);
    }

//    전체 회원 정보 조회
    public List<UserVO> findAllUser() {
        return userMapper.selectAllUser();
    }

//    단일 회원 정보 조회
    public Optional<UserVO> findUser(String userEmail) {
        return userMapper.selectUser(userEmail);
    }

//    로그인
    public String login(UserVO userVO) {
        return userMapper.login(userVO);
    }

//    아이디 중복 체크
    public int checkIdExist(String userIdentification) {
        return userMapper.checkId(userIdentification);
    }

//    이메일 중복 체크
    public int checkEmailExist(String userEmail) {
        return userMapper.checkEmail(userEmail);
    }

//    비밀번호 중복 체크
    public int checkPasswordExist(UserVO userVO) {
        return userMapper.checkPassword(userVO);
    }

//    아이디 조회
    public String findIdByEmailAndName(UserVO userVO) {
        return userMapper.selectIdByEmailAndName(userVO);
    }

//    비밀번호 조회
    public String findPasswordByEmail(String userEmail) {
        return userMapper.selectPasswordByEmail(userEmail);
    }

//    이메일 조회
    public String findEmailById(Long id) {
        return userMapper.selectEmailById(id);
    }

//    회원 정보 수정
    public void updateUser(UserVO userVO) {
        userMapper.updateUser(userVO);
    }

//    비밀번호 변경
    public void updatePassword(UserVO userVO) {
        userMapper.updatePassword(userVO);
    }

//    회원 탈퇴
    public void deleteUser(String userEmail) {
        userMapper.deleteUser(userEmail);
    }

    public Long findIdByEmail(String userEmail) {
        return userMapper.selectIdByEmail(userEmail);
    }
}
