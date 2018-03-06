package com.mygaienko.service;

import com.mygaienko.TransactionalApplication;
import com.mygaienko.model.AlfaEntity;
import com.mygaienko.model.BetaEntity;
import com.mygaienko.repository.AlfaRepository;
import com.mygaienko.repository.BetaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.NestedTransactionNotSupportedException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = TransactionalApplication.class)
@AutoConfigureMockMvc
public class AlfaServiceIntegrationTest {

    @Autowired
    private AlfaService alfaService;

    @Autowired
    private AlfaRepository alfaRepository;

    @Autowired
    private BetaRepository betaRepository;

    @Before
    public void setUp() {
        alfaRepository.save(new AlfaEntity(1L, "alfa"));
        betaRepository.save(new BetaEntity(1L, "beta"));
    }

    @Test
    public void testExecuteRequiredWithRequiresNew() {
        alfaService.executeRequiredWithRequiresNew();
        assertEquals("alfa", betaRepository.findOne(1L).getName());
    }

    @Test
    public void testExecuteRequiredWithSupports() {
        alfaService.executeRequiredWithSupports();
        assertEquals("alfa amended", betaRepository.findOne(1L).getName());
    }

    @Test(expected = NestedTransactionNotSupportedException.class)
    public void testExecuteRequiredWithNested() {
        alfaService.executeRequiredWithNested();
        assertEquals("alfa amended", betaRepository.findOne(1L).getName());
    }

    @Test
    public void testExecuteRequiredWithNotSupported() {
        alfaService.executeRequiredWithNotSupported();
        assertEquals("alfa", betaRepository.findOne(1L).getName());
    }
}