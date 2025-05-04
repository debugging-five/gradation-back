package com.app.gradationback.service;

import com.app.gradationback.domain.UserVO;
import com.app.gradationback.repository.*;
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
    private final ArtDAO artDAO;
    private final ArtImgDAO artImgDAO;
    private final ArtPostDAO artPostDAO;

    //    회원가입
    @Override
    public void join(UserVO userVO) {
        userDAO.save(userVO);
    }

//    전체 회원 조회
    @Override
    public List<UserVO> getUserList() {
        return userDAO.findAllUser();
    }

//    단일 회원 조회 (이메일로)
    @Override
    public Optional<UserVO> getUserByEmail(String userEmail) {
        return userDAO.findUserByEmail(userEmail);
    }

//    단일 회원 조회 (아이디로)
    @Override
    public Optional<UserVO> getUserByIdentification(String userIdentification) {
        return userDAO.findUserByIdentification(userIdentification);
    }

//    로그인
    @Override
    public String login(UserVO userVO) {
        return userDAO.login(userVO);
    }

//    아이디 찾기 (이름 + 이메일)
    @Override
    public String getIdentificationByEmailAndName(UserVO userVO) {
        return userDAO.findIdentificationByEmailAndName(userVO);
    }

//    비밀번호 찾기 (이메일)
    @Override
    public String getPasswordByEmail(String userEmail) {
        return userDAO.findPasswordByEmail(userEmail);
    }

//    아이디로 이메일 조회
    @Override
    public String getEmailById(Long id) {
        return userDAO.findEmailById(id);
    }

//    이메일로 ID 조회
    @Override
    public Long getIdByEmail(String userEmail) {
        return userDAO.findIdByEmail(userEmail);
    }

//    회원 정보 수정
    @Override
    public void modifyUser(UserVO userVO) {
        userDAO.updateUser(userVO);
    }

//    회원 탈퇴 (댓글, 게시글 삭제)
    @Override
    public void withdraw(String userEmail) {
        Long userId = userDAO.findIdByEmail(userEmail);
//        댓글 삭제
        commentDAO.deleteAllByUserId(userId);
//        게시글 삭제
        artPostDAO.deleteAllByUserId(userId);
//        회원 탈퇴
        userDAO.deleteUser(userEmail);
    }

}
