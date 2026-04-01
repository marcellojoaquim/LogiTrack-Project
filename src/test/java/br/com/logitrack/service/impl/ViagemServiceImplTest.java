package br.com.logitrack.service.impl;

import br.com.logitrack.exception.BusinessException;
import br.com.logitrack.model.Veiculo;
import br.com.logitrack.model.Viagem;
import br.com.logitrack.model.dto.VeiculoDTO;
import br.com.logitrack.model.dto.ViagemDTO;
import br.com.logitrack.repository.IVeiculoRepository;
import br.com.logitrack.repository.IViagemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ViagemServiceImplTest {

    @Mock
    IViagemRepository viagemRepository;

    @Mock
    IVeiculoRepository veiculoRepository;

    @Mock
    VeiculoServiceImpl veiculoServiceImpl;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    ViagemServiceImpl viagemService;

    @Test
    void salvar() {

        Long idVeiculo = 1L;
        ViagemDTO viagemDTO = new ViagemDTO();
        viagemDTO.setIdVeiculo(idVeiculo);

        VeiculoDTO veiculoRetorno = new VeiculoDTO();

        when(veiculoServiceImpl.buscarVeiculoPorId(idVeiculo)).thenReturn(veiculoRetorno);
        when(viagemRepository.save(any(ViagemDTO.class))).thenReturn(viagemDTO);
        ViagemDTO result = viagemService.salvar(viagemDTO);

        assertNotNull(result);
        assertEquals(idVeiculo, result.getIdVeiculo());

        verify(veiculoServiceImpl, times(1)).buscarVeiculoPorId(idVeiculo);
        verify(viagemRepository, times(1)).save(any(ViagemDTO.class));
    }

    @Test
    void atualizar() {

        Long idViagem = 1l;
        ViagemDTO viagemDTO = new ViagemDTO();

        Viagem viagem = new Viagem();

        when(viagemRepository.findById(idViagem)).thenReturn(Optional.of(viagem));
        when(viagemRepository.save(any(ViagemDTO.class))).thenReturn(viagemDTO);

        ViagemDTO resultado = viagemService.atualizar(idViagem,  viagemDTO);
        assertNotNull(resultado);
        verify(viagemRepository, times(1)).findById(idViagem);
        verify(viagemRepository, times(1)).save(any(ViagemDTO.class));

    }

    @Test
    void findById() {
        Long idViagem = 1l;
        Viagem viagem = new Viagem();
        ViagemDTO viagemDTO = new ViagemDTO();

        when(viagemRepository.findById(idViagem)).thenReturn(Optional.of(viagem));
        when(modelMapper.map(any(), eq(ViagemDTO.class))).thenReturn(viagemDTO);

        java.util.Optional<ViagemDTO> result = viagemService.findById(idViagem);
        assertNotNull(result);
        verify(viagemRepository, times(1)).findById(idViagem);
    }

    @Test
    void encerrar() {
        Instant instant = Instant.now();
        Long idViagem = 1L;

        Viagem viagem = new Viagem();
        viagem.setId(idViagem);
        viagem.setDataChegada(null);

        when(viagemRepository.findById(idViagem)).thenReturn(Optional.of(viagem));
        when(viagemRepository.save(any(Viagem.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ViagemDTO result = viagemService.encerrar(idViagem, instant);

        verify(viagemRepository, times(1)).findById(idViagem);
        verify(viagemRepository, times(1)).save(any(Viagem.class));
    }

    @Test
    void findAllByIdVeiculo() {
        List<ViagemDTO> viagemDTOList = new ArrayList<>();
        Long idVeiculo = 1L;
        Long idViagem = 1L;
        Viagem viagem = new Viagem();
        viagem.setId(idVeiculo);
        viagem.setId(idViagem);

        ViagemDTO viagemDTO = new ViagemDTO();
        viagemDTOList.add(viagemDTO);

        when(viagemRepository.findAllByIdVeiculo(idVeiculo)).thenReturn(viagemDTOList);
        List<ViagemDTO> result = viagemService.findAllByIdVeiculo(idVeiculo);

        assertNotNull(result);
        assertEquals(result.size(), viagemDTOList.size());
    }

    @Test
    void kmTotal() {
        BigDecimal kmEsperada = new BigDecimal("1500.50");
        when(viagemRepository.kmTotal()).thenReturn(kmEsperada);

        BigDecimal resultado = viagemService.kmTotal();

        assertEquals(kmEsperada, resultado);
        verify(viagemRepository, times(1)).kmTotal();
    }

    @Test
    void kmTotalPorVeiculo() {
        Long idVeiculo = 1L;
        BigDecimal kmEsperada = new BigDecimal("500.00");

        when(veiculoServiceImpl.existsById(idVeiculo)).thenReturn(true);
        when(viagemRepository.kmTotalPorVeiculo(idVeiculo)).thenReturn(kmEsperada);

        BigDecimal resultado = viagemService.kmTotalPorVeiculo(idVeiculo);

        assertEquals(kmEsperada, resultado);
        verify(veiculoServiceImpl).existsById(idVeiculo);
        verify(viagemRepository).kmTotalPorVeiculo(idVeiculo);
    }

    @Test
    void volumePorCategoria() {
        String categoria = "PESADOS";
        Long volumeEsperado = 100L;
        when(viagemRepository.volumePorCategoria(categoria)).thenReturn(volumeEsperado);

        Long resultado = viagemService.volumePorCategoria(categoria);

        assertEquals(volumeEsperado, resultado);
        verify(viagemRepository).volumePorCategoria(categoria);
    }
}