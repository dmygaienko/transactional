package com.mygaienko.service;

import com.mygaienko.model.AlfaEntity;
import com.mygaienko.model.BetaEntity;
import com.mygaienko.repository.AlfaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



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

    @Transactional(propagation = Propagation.REQUIRED)
    public void executeRequiredWithRequiresNew() {
        amendAlfa();
        self.amendBetaWithAlfaRequiresNew();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void amendBetaWithAlfaRequiresNew() {
        amendBetaWithAlfa();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void executeRequiredWithSupports() {
        amendAlfa();
        self.amendBetaWithAlfaSupports();
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void amendBetaWithAlfaSupports() {
        amendBetaWithAlfa();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void executeRequiredWithNotSupported() {
        amendAlfa();
        self.amendBetaWithAlfaNotSupported();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void amendBetaWithAlfaNotSupported() {
        amendBetaWithAlfa();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void executeRequiredWithNested() {
        amendAlfa();
        self.amendBetaWithAlfaNested();
    }

    @Transactional(propagation = Propagation.NESTED)
    public void amendBetaWithAlfaNested() {
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
