package com.app.gradationback.repository;

import com.app.gradationback.domain.BanDTO;
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

//    일반 회원가입
    public void saveNormal(UserVO userVO) {
        userMapper.insertNormal(userVO);
    }

//    소셜 로그인 후 회원가입
    public void saveSocial(UserVO userVO) { userMapper.insertSocial(userVO); }

//    전체 회원 조회
    public List<UserVO> findAllUser() {
        return userMapper.selectAllUser();
    }

//    단일 회원 조회 (이메일로)
    public Optional<UserVO> findUserByEmail(String userEmail) {
        return userMapper.selectUserByEmail(userEmail);
    }

//    단일 회원 조회 (아이디로)
    public Optional<UserVO> findUserByIdentification(String userIdentification) {
        return userMapper.selectUserByIdentification(userIdentification);
    }

//    로그인
    public String login(UserVO userVO) {
        return userMapper.login(userVO);
    }

//    아이디 찾기 (이름 + 이메일)
    public String findIdentificationByEmailAndName(UserVO userVO) {
        return userMapper.selectIdentificationByEmailAndName(userVO);
    }

//    비밀번호 찾기 (이메일)
    public String findPasswordByEmail(String userEmail) {
        return userMapper.selectPasswordByEmail(userEmail);
    }

//    아이디로 이메일 조회
    public String findEmailById(Long id) {
        return userMapper.selectEmailById(id);
    }

//    이메일로 ID 조회
    public Long findIdByEmail(String userEmail) {
        return userMapper.selectIdByEmail(userEmail);
    }

//    회원 정보 수정
    public void updateUser(UserVO userVO) {
        userMapper.updateUser(userVO);
    }

//    회원 탈퇴
    public void deleteUser(String userEmail) {
        userMapper.deleteUser(userEmail);
    }

//    관리자용 유저 정지 처리
    public void insertBan(BanDTO banDTO){ userMapper.insertBan(banDTO);}

//    관리자용 유저 정지 상태 업데이트
    public void updateUserBanStatus(BanDTO banDTO){userMapper.updateUserBanStatus(banDTO);}

    public Optional<UserVO> findUserByIdForWrite(Long id) {return userMapper.selectUserByIdForWrite(id);
    }


}
