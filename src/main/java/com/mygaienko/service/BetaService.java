package com.mygaienko.service;

import com.mygaienko.model.BetaEntity;
import com.mygaienko.repository.BetaRepository;
import org.springframework.stereotype.Service;

@Service
public class BetaService {

    private final BetaRepository betaRepository;

    public BetaService(BetaRepository betaRepository) {
        this.betaRepository = betaRepository;
    }

    public BetaEntity save(BetaEntity betaEntity) {
        return betaRepository.save(betaEntity);
    }

    public BetaEntity findOne(Long aLong) {
        return betaRepository.findOne(aLong);
    }

    public boolean exists(Long aLong) {
        return betaRepository.exists(aLong);
    }

    public Iterable<BetaEntity> findAll() {
        return betaRepository.findAll();
    }
}
