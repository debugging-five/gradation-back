package com.app.gradationback.service;

import com.app.gradationback.domain.ArtLikeVO;

public interface ArtLikeService {

//    좋아요
    public void register(ArtLikeVO artLikeVO);

//    좋아요 수
    public int getLikeCount(Long artId);

//    좋아요 삭제
    public void remove(ArtLikeVO artLikeVO);

//    좋아요 전체 삭제
    public void removeAll(Long artId);
}
