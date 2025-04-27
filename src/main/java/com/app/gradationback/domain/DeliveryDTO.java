package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;


@Component
@Data
public class DeliveryDTO {
//    TBL_DELIVERY
    private Long id;
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
    private boolean userAgreement;
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
    private Long majorId;

//    TBL_ART
    private String artTitle;
    private String artCategory;
    private String artMaterial;
    private String artSize;
    private String artDescription;
    private String artEndDate;
    private String artStatus;
    private Long userId;

//    TBL_ART_IMG
    private String artImgName;
    private String artImgPath;
    private Long artId;
}
