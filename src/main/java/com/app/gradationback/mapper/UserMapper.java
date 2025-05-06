package com.app.gradationback.mapper;

import com.app.gradationback.domain.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

//    회원가입
    public void insert(UserVO userVO);

//    전체 회원 조회
    public List<UserVO> selectAllUser();

//    단일 회원 조회 (이메일로)
    public Optional<UserVO> selectUserByEmail(String userEmail);

//    단일 회원 조회 (아이디로)
    public Optional<UserVO> selectUserByIdentification(String userIdentification);

//    로그인
    public String login(UserVO userVO);

//    아이디 찾기 (이름 + 이메일)
    public String selectIdentificationByEmailAndName(UserVO userVO);

//    비밀번호 찾기 (이메일)
    public String selectPasswordByEmail(String userEmail);

//    아이디로 이메일 조회
    public String selectEmailById(Long id);

//    이메일로 ID 조회
    public Long selectIdByEmail(String userEmail);

//    회원 정보 수정
    public void updateUser(UserVO userVO);

//    회원 탈퇴
    public void deleteUser(String userEmail);
}
