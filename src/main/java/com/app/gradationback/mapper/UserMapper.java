package com.app.gradationback.mapper;

import com.app.gradationback.domain.BanDTO;
import com.app.gradationback.domain.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

//    일반 회원가입
    public void insertNormal(UserVO userVO);

//    소셜 로그인 후 회원가입
    public void insertSocial(UserVO userVO);

//    전체 회원 조회
    public List<UserVO> selectAllUser();

//    단일 회원 조회 (이메일로)
    public Optional<UserVO> selectUserByEmail(String userEmail);

//    단일 회원 조회 (아이디로)
    public Optional<UserVO> selectUserByIdentification(String userIdentification);

//    로그인
    public String login(UserVO userVO);

//    아이디 찾기 (이름 + 이메일)
    public UserVO selectIdentificationByEmailAndName(UserVO userVO);

//    비밀번호 찾기 (이메일)
//    public String selectPasswordByEmail(String userEmail);
    public UserVO selectPasswordByEmail(UserVO userVO);

//    아이디로 이메일 조회
    public String selectEmailById(Long id);

//    이메일로 ID 조회
    public Long selectIdByEmail(String userEmail);

//    회원 정보 수정
    public void updateUser(UserVO userVO);

//    회원 프로필 이미지 수정
    public void updateProfileImg(UserVO userVO);

//    비밀번호 수정
    public void updatePassword(UserVO userVO);

//    대학교 인증
    public void updateUniversityStatus(UserVO userVO);

//    회원 탈퇴
    public void deleteUser(String userEmail);

//    관리자용 유저 정지 처리
    public void insertBan(BanDTO banDTO);

//    관리자용 유저 정지 상태 업데이트
    public void updateUserBanStatus(BanDTO banDTO);

//    댓글 작성 시 정지 유저 확인용
    public Optional<UserVO> selectUserByIdForWrite(Long id);

}
