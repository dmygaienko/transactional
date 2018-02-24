package com.mygaienko.service;

import com.mygaienko.model.AlfaEntity;
import com.mygaienko.model.BetaEntity;
import com.mygaienko.repository.AlfaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.NOT_SUPPORTED;
import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.REQUIRES_NEW;
import static javax.transaction.Transactional.TxType.SUPPORTS;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AlfaService {

    @Autowired
    private AlfaService self;

    private final AlfaRepository alfaRepository;
    private final BetaService betaService;

    public AlfaService(AlfaRepository alfaRepository, BetaService betaService) {
        this.alfaRepository = alfaRepository;
        this.betaService = betaService;
    }

    @Transactional(REQUIRED)
    public void executeRequiredWithRequiresNew() {
        amendAlfa();
        self.amendBetaWithAlfaRequiresNew();
    }

    @Transactional(REQUIRES_NEW)
    public void amendBetaWithAlfaRequiresNew() {
        amendBetaWithAlfa();
    }

    @Transactional(REQUIRED)
    public void executeRequiredWithSupports() {
        amendAlfa();
        self.amendBetaWithAlfaSupports();
    }

    @Transactional(SUPPORTS)
    public void amendBetaWithAlfaSupports() {
        amendBetaWithAlfa();
    }

    @Transactional(REQUIRED)
    public void executeRequiredWithNotSupported() {
        amendAlfa();
        self.amendBetaWithAlfaNotSupported();
    }

    @Transactional(NOT_SUPPORTED)
    public void amendBetaWithAlfaNotSupported() {
        amendBetaWithAlfa();
    }

    private void amendBetaWithAlfa() {
        AlfaEntity alfa = alfaRepository.findOne(1L);
        BetaEntity beta = betaService.findOne(1L);
        beta.setName(alfa.getName());
        betaService.save(beta);
    }

    private void amendAlfa() {
        AlfaEntity alfa = alfaRepository.findOne(1L);
        alfa.setName("alfa amended");
        alfaRepository.save(alfa);
    }
}
