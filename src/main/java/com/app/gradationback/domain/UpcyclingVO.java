package com.app.gradationback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class UpcyclingVO {
    private Long id;
    private String upcyclingCategory;
    private String upcyclingSize;
    private Date upcyclingDate;
    private String upcyclingAddress;
    private String upcyclingImgName;
    private String upcyclingImgPath;
    private String upcyclingSignificant;
    private String upcyclingStatus;
    private Long userId;
}
