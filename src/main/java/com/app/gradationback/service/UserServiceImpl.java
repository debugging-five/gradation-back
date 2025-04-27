package com.app.gradationback.service;

import com.app.gradationback.domain.UserVO;
import com.app.gradationback.repository.CommentDAO;
import com.app.gradationback.repository.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final CommentDAO commentDAO;

//    회원가입
    @Override
    public void join(UserVO userVO) {
        userDAO.save(userVO);
    }

//    전체 회원 정보 조회
    @Override
    public List<UserVO> getUserList() {
        return userDAO.findAllUser();
    }

//    단일 회원 정보 조회
    @Override
    public Optional<UserVO> getUser(String userEmail) {
        return userDAO.findUser(userEmail);
    }

//    로그인
    @Override
    public String login(UserVO userVO) {
        return userDAO.login(userVO);
    }

//    아이디 중복 체크
    @Override
    public int isIdExist(String userIdentification) {
        return userDAO.checkIdExist(userIdentification);
    }

//    이메일 중복 체크
    @Override
    public int isEmailExist(String userEmail) {
        return userDAO.checkEmailExist(userEmail);
    }

//    비밀번호 중복 체크
    @Override
    public int isPasswordExist(UserVO userVO) {
        return userDAO.checkPasswordExist(userVO);
    }

//    아이디 조회
    @Override
    public String getIdByEmailAndName(UserVO userVO) {
        return userDAO.findIdByEmailAndName(userVO);
    }

//    비밀번호 조회
    @Override
    public String getPasswordByEmail(String userEmail) {
        return userDAO.findPasswordByEmail(userEmail);
    }

//    이메일 조회
    @Override
    public String getEmailById(Long id) {
        return userDAO.findEmailById(id);
    }

//    회원 정보 수정
    @Override
    public void modifyUser(UserVO userVO) {
        userDAO.updateUser(userVO);
    }

//    비밀번호 변경
    @Override
    public void modifyPassword(UserVO userVO) {
        userDAO.updatePassword(userVO);
    }

//    회원 탈퇴
    @Override
    public void withdraw(String userEmail) {
        Long userId = userDAO.findIdByEmail(userEmail);
        commentDAO.deleteAllByUserId(userId);
        userDAO.deleteUser(userEmail);
    }

    @Override
    public Long getIdByEmail(String userEmail) {
        return userDAO.findIdByEmail(userEmail);
    }
}
