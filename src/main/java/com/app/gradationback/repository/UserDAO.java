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
        return userMapper.selectUserByEmail(userEmail);
    }

//    아이디 중복 체크
    public int checkIdExist(String userIdentification) {
        return userMapper.checkId(userIdentification);
    }

//    이메일 중복 체크
    public int checkEmailExist(String userEmail) {
        return userMapper.checkEmail(userEmail);
    }

//    아이디 찾기
    public String findId(UserVO userVO) {
        return userMapper.selectIdByEmailAndName(userVO);
    }

//    회원 조회
    public int findUserByIdEmailAndName(UserVO userVO) {
        return userMapper.selectByIdAndEmailAndName(userVO);
    }

//    로그인
    public String authenticateUser(UserVO userVO) {
        return userMapper.login(userVO);
    }

//    비밀번호 변경
    public void updatePassword(UserVO userVO) {
        userMapper.updatePassword(userVO);
    }

//    전공 조회

//    회원 정보 수정
    public void updateUser(UserVO userVO) {
        userMapper.updateUser(userVO);
    }

//    비밀번호 조회
    public String findPassword(String userEmail) {
        return userMapper.selectPassword(userEmail);
    }

//    회원 탍퇴
    public void deleteUser(String userEmail) {
        userMapper.deleteUser(userEmail);
    }

//    이메일 찾기
    public String findEmail(Long id) {
        return userMapper.findEmailById(id);
    }
}
