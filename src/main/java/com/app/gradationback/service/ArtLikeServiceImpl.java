package com.app.gradationback.service;

import com.app.gradationback.domain.ArtLikeVO;
import com.app.gradationback.repository.ArtDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ArtLikeServiceImpl implements ArtLikeService {

    private final ArtDAO artDAO;

//    좋아요 등록
    @Override
    public void register(ArtLikeVO artLikeVO) {
        if(!getLiked(artLikeVO)) {
            artDAO.saveLike(artLikeVO);
        }
    }

//    좋아요 수
    @Override
    public int getLikeCount(Long artId) {
        return artDAO.findLikeCount(artId);
    }

//    좋아요 여부
    @Override
    public boolean getLiked(ArtLikeVO artLikeVO) {
        if(artDAO.findLiked(artLikeVO) == 1) {
            return true;
        }
        return false;
    }

//    좋아요 삭제
    @Override
    public void remove(ArtLikeVO artLikeVO) {
        artDAO.deleteLike(artLikeVO);
    }

//    좋아요 전체 삭제
    @Override
    public void removeAll(Long artId) {
        artDAO.deleteAllLike(artId);
    }
}
