package com.app.gradationback.service;

import com.app.gradationback.domain.UpcyclingVO;
import com.app.gradationback.repository.UpcyclingDAO;

import java.util.List;
import java.util.Optional;

public class UpcyclingServiceImpl implements UpcyclingService {

    private UpcyclingDAO upcyclingDAO;

    @Override
    public List<UpcyclingVO> getAll() {
        return List.of();
    }

    @Override
    public Optional<UpcyclingVO> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public void register(UpcyclingVO upcyclingVO) {

    }

    @Override
    public void modify(UpcyclingVO upcyclingVO) {

    }

    @Override
    public void remove(Long id) {

    }
}
