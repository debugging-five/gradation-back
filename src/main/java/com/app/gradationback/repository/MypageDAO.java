package com.app.gradationback.repository;

import com.app.gradationback.mapper.MypageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MypageDAO {

    private final MypageMapper mypageMapper;

    // 회원 탈퇴 관련 연관 데이터 모두 삭제
    public void deleteUserAllData(Long userId) {
        // 좋아요
        mypageMapper.deleteArtLikesByUserId(userId);
        mypageMapper.deleteArtLikesByArtOfUser(userId);

        // 전시 관련
        mypageMapper.deleteExhibitionPastArtByArtOfUser(userId);
        mypageMapper.deleteArtImgByArtOfUser(userId);
        mypageMapper.deleteCommentByArtPostOfUser(userId);

        // 게시글, 작품
        mypageMapper.deleteArtPostByUserId(userId);
        mypageMapper.deleteArtPostsByUserId(userId);
        mypageMapper.deleteArtByUserId(userId);

        // 경매
        mypageMapper.deleteAuctionBiddingByUserId(userId);
        mypageMapper.deleteAuctionByUserId(userId);

        // 결제 및 배송
        mypageMapper.deletePaymentCancellationByUserId(userId);
        mypageMapper.deletePaymentByUserId(userId);
        mypageMapper.deleteDeliveryByUserId(userId);

        // 기타
        mypageMapper.deleteBanByUserId(userId);
        mypageMapper.deleteQnaAnswerByUserId(userId);
        mypageMapper.deleteQnaByUserId(userId);
        mypageMapper.deleteUpcyclingByUserId(userId);
        mypageMapper.deleteUniversityLikeByUserId(userId);
        mypageMapper.deleteHistoryByUserId(userId);

        // 최종적으로 사용자 정보 삭제
        mypageMapper.deleteUserById(userId);
    }

    // 기존 단일 회원 삭제 메서드도 남겨둠 (필요시)
    public void deleteUser(Long id) {
        mypageMapper.deleteUserById(id);
    }
}
