package com.app.gradationback.service;

import com.app.gradationback.domain.AuctionDTO;
import com.app.gradationback.domain.AuctionVO;
import com.app.gradationback.repository.AuctionDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {

    private final AuctionDAO auctionDAO;

    @Override
    public void auctionRegistration(AuctionVO auctionVO) {
        auctionDAO.insert(auctionVO);
    }

    @Override
    public List<AuctionDTO> auctionList(HashMap<String, Object> params) {
        return auctionDAO.selectAll(params);
    }

    @Override
    public List<AuctionDTO> auctionRead(Long id) {
        return auctionDAO.selectById(id);
    }

    @Override
    public void auctionModify(AuctionVO auctionVO) {
        auctionDAO.update(auctionVO);
    }

    @Override
    public void auctionDelete(Long id) {
        auctionDAO.delete(id);
    }
}
