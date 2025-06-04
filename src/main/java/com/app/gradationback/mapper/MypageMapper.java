package com.app.gradationback.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MypageMapper {
    void deleteArtByAuction (Long userId);
    void deleteArtLikesByUserId(Long userId);
    void deleteArtLikesByArtOfUser(Long userId);
    void deleteExhibitionPastArtByArtOfUser(Long userId);
    void deleteArtImgByArtOfUser(Long userId);
    void deleteCommentByArtPostOfUser(Long userId);

    void deleteArtPostsByUserId(Long userId);
    void deleteArtByUserId(Long userId);
    void deleteAuctionBiddingByUserId(Long userId);
    void deleteAuctionByUserId(Long userId);
    void deletePaymentCancellationByUserId(Long userId);

    void deletePaymentByUserId(Long userId);
    void deleteDeliveryByUserId(Long userId);
    void deleteBanByUserId(Long userId);
    void deleteQnaAnswerByUserId(Long userId);
    void deleteQnaByUserId(Long userId);

    void deleteUpcyclingByUserId(Long userId);
    void deleteUniversityLikeByUserId(Long userId);
    void deleteHistoryByUserId(Long userId);
    void deleteUserById(Long id);
}
