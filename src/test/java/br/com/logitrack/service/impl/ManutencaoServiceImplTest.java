package br.com.logitrack.service.impl;

import br.com.logitrack.repository.IManutencaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ManutencaoServiceImplTest {

    @InjectMocks
    private ManutencaoServiceImpl manutencaoServiceImpl;

    @Mock
    IManutencaoRepository manutencaoRepository;

    @Mock
    private ModelMapper mapper;

    private final BigDecimal projecaoTest = new BigDecimal("1500.00");


    @Test
    void projecaoFinanceiraMesAtual() {
        when(manutencaoRepository.projecaoFinanceiraMesAtual()).thenReturn(projecaoTest);

        BigDecimal result = manutencaoServiceImpl.projecaoFinanceiraMesAtual();

        assertEquals(projecaoTest, result);
        verify(manutencaoRepository, times(1)).projecaoFinanceiraMesAtual();
    }
}