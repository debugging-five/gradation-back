package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Component
@Data
public class AuctionDTO {
//    TBL_AUCTION
    private Long id;
    private String auctionStartDate;
    private String auctionEndDate;
    private Long auctionStartPrice;
    private String auctionEstimatedMinPrice;
    private String auctionEstimatedMaxPrice;
    private boolean auctionAttracted;
    private Long auctionBidPrice;
    private String auctionBidDate;
    private Long artId;
    private Long userId;

//    TBL_AUCTION_BIDDING
    private Long auctionBiddingPrice;
    private boolean auctionBiddingAutoOk;
    private Timestamp auctionBiddingTime;
    private Long auctionId;

//    TBL_USER
    private String userImgName;
    private String userImgPath;
    private String userName;
    private String userEmail;
    private String userIdentification;
    private String userPassword;
    private String userPhone;
    private String userNickName;
    private String userAddress;
    private String userDetailAddress;
    private String postalCode;
    private boolean userSnsOk;
    private boolean userMailOk;
    private boolean userAgreementOk;
    private String userIntroduce;
    private String userInstagram;
    private String userYoutube;
    private String userBlog;
    private String userKakao;
    private String userGoogle;
    private String userNaver;
    private boolean userAdminOk;
    private boolean userBanOk;
    private String userMajorImgName;
    private String userMajorImgPath;
    private String userWriterStatus;
    private String userUniversityStatus;
    private String userArtCategory;
    private String userBackgroundImgName;
    private String userBackgroundImgPath;
    private String userProvider;
    private Long majorId;

//    TBL_DELIVERY
    private String deliveryAddress;
    private String deliveryDetailAddress;
    private String deliveryPostalCode;
    private String deliveryState;
    private String deliveryMessage;
    private String deliveryReceiver;
    private String deliveryPhone;
    private Long paymentId;

//    TBL_PAYMENT
    private String paymentMethod;
    private int paymentAmount;
    private String paymentDate;
    private String paymentCode;

//    TBL_ART
    private String artTitle;
    private String artCategory;
    private String artMaterial;
    private String artSize;
    private String artDescription;
    private String artEndDate;
    private String artStatus;

//    TBL_ART_IMG
    private List<ArtImgVO> argImgList;

    private String artImgName;
    private String artImgPath;

//    ARTIST_INFO
    private Long artistId;
    private String artistName;
}

