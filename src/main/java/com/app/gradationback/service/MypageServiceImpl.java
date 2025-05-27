package com.app.gradationback.service;

import com.app.gradationback.repository.MypageDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MypageServiceImpl implements MypageService {

    private final MypageDAO mypageDAO;

    @Override
    public void withdrawAll(Long userId) {
        mypageDAO.deleteUserAllData(userId);
    }


}
