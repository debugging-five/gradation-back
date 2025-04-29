package com.app.gradationback.service;

import com.app.gradationback.domain.UpcyclingVO;
import com.app.gradationback.repository.UpcyclingDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class UpcyclingServiceImpl implements UpcyclingService {

    private final UpcyclingDAO upcyclingDAO;

    @Override
    public List<UpcyclingVO> getUpcyclingUserList() {
        return upcyclingDAO.getUpcyclingUserList();
    }

    @Override
    public Optional<UpcyclingVO> getByUpcyclingUser(Long id) {
        return upcyclingDAO.getByUpcyclingUser(id);
    }

    @Override
    public void register(UpcyclingVO upcyclingVO) {
        upcyclingDAO.save(upcyclingVO);
    }

    @Override
    public void modify(UpcyclingVO upcyclingVO) {
        upcyclingDAO.update(upcyclingVO);
    }

    @Override
    public void remove(Long id) {
        upcyclingDAO.delete(id);
    }
}
