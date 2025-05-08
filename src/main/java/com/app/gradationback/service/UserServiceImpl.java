package com.app.gradationback.service;

import com.app.gradationback.domain.*;
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
//        Long userId = userDAO.findIdByEmail(userEmail);
//
////        댓글 삭제
//        commentDAO.deleteAllByUserId(userId);
////        게시글 삭제
////        artPostDAO.deleteAllByUserId(userId);
////        회원 탈퇴
////        userDAO.deleteUser(userEmail);
//        List<ArtPostDTO> postList = artPostDAO.findAllByUserId(userId);
//
//        for (ArtPostDTO post : postList) {
//            Long postId = post.getId();
//            Long artId = post.getArtId();
//
//            artPostDAO.deleteById(postId);
//            artImgDAO.deleteAllByArtId(artId);
//            artDAO.deleteById(artId);
//        }
//
//        // 3. 회원 삭제
//        userDAO.deleteUser(userEmail);

        Long userId = userDAO.findIdByEmail(userEmail);

        // 1. 댓글 좋아요 → 댓글 삭제
//        commentDAO.deleteAllByUserId(userId);
//
//        List<ArtPostDTO> postList = artPostDAO.findAllByUserId(userId);
//        for (ArtPostDTO post : postList) {
//            Long postId = post.getId();
//            Long artId = post.getArtId();
//
//            commentDAO.deleteAllByPostId(postId); // 있다면
//
//            artPostDAO.deleteById(postId);
//            artImgDAO.deleteAllByArtId(artId);
//            artDAO.deleteById(artId);
//    }


//        commentDAO.deleteAllByUserId(userId);
//        List<ArtPostDTO> postList = artPostDAO.findAllByUserId(userId);
//        for (ArtPostDTO post : postList) {
//            Long postId = post.getId();
//            Long artId = post.getArtId();
//        commentDAO.deleteAllByPostId(postId);
//        // 2. 모든 작품 + 게시글 + 이미지 삭제
//        List<ArtVO> artList = artDAO.findAllByUserId(userId);
//        for (ArtVO art : artList) {
//            Long artId = art.getId();
//
//            artPostDAO.deleteAllByArtId(artId);   // 게시했든 안 했든 제거
//            artImgDAO.deleteAllByArtId(artId);
//            artDAO.deleteById(artId);
//        }
//
//        // 3. 유저 삭제
//        userDAO.deleteUser(userEmail);

        // 1. 댓글 삭제 (댓글 좋아요 등도 있다면 여기에 추가)
        commentDAO.deleteAllByUserId(userId);

        // 2. 게시글 삭제 전: 댓글 먼저 삭제 → 게시글 삭제
        List<ArtPostDTO> postList = artPostDAO.findAllByUserId(userId);
        for (ArtPostDTO post : postList) {
            Long postId = post.getId();           // 게시글 ID

            // 댓글 먼저 삭제
            commentDAO.deleteAllByPostId(postId);

            // 게시글 삭제
            artPostDAO.deleteById(postId);
        }

        // 3. 유저가 등록한 모든 작품 삭제 (게시되지 않은 것도 포함)
        List<ArtVO> artList = artDAO.findAllByUserId(userId);
        for (ArtVO art : artList) {
            Long artId = art.getId();

            // 이미지 삭제
            artImgDAO.deleteAllByArtId(artId);

            // 작품 삭제
            artDAO.deleteById(artId);
        }

        // 4. 최종 회원 삭제
        userDAO.deleteUser(userEmail);

    }

//    관리자용 유저 정지 처리 및 상태 변경
    @Override
    public void banUser(BanDTO banDTO) {
//        Ban 테이블에 정지 기록 저장
        userDAO.insertBan(banDTO);
//        User 테이블에 정시 상태(댓글, 영구정지) 반영
        userDAO.updateUserBanStatus(banDTO);
    }
//    관리자용 유저 정지 해제
    @Override
    public void updateUserBanStatus(BanDTO banDTO) {
        userDAO.updateUserBanStatus(banDTO);
    }
//    댓글 작성 시 정지 유저 확인
    @Override
    public Optional<UserVO> findUserByIdForWrite(Long id){
        return userDAO.findUserByIdForWrite(id);
    };

}
