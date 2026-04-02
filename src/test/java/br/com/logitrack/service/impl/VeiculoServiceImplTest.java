package br.com.logitrack.service.impl;

import br.com.logitrack.model.Veiculo;
import br.com.logitrack.model.dto.VeiculoDTO;
import br.com.logitrack.repository.IVeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class VeiculoServiceImplTest {

    @InjectMocks
    VeiculoServiceImpl service;

    @Mock
    IVeiculoRepository repository;

    @Mock
    ModelMapper modelMapper;

    private Veiculo veiculo;
    private VeiculoDTO veiculoDTO;
    private final String PLACA = "ABC-1234";
    private final Long ID = 1l;


    @BeforeEach
    void setUp() {
        veiculoDTO = VeiculoDTO.builder()
                .placa(PLACA)
                .ano(2000)
                .tipo(Veiculo.TipoVeiculo.LEVE)
                .modelo("Hatch")
                .build();
        veiculo = new Veiculo();
        veiculo.setPlaca(PLACA);
        veiculo.setAno(veiculoDTO.getAno());
        veiculo.setTipo(veiculoDTO.getTipo());
        veiculo.setModelo(veiculoDTO.getModelo());

    }

    @Test
    void cadastrarVeiculo() {
        when(repository.existsByPlaca(PLACA)).thenReturn(false);
        when(repository.save(any(Veiculo.class))).thenReturn(veiculo);
        when(modelMapper.map(any(Veiculo.class), eq(VeiculoDTO.class))).thenReturn(veiculoDTO);

        VeiculoDTO result = service.cadastrarVeiculo(veiculoDTO);
        Mockito.verify(repository, Mockito.times(1)).save(any(Veiculo.class));
        assertEquals(veiculo.getPlaca(), result.getPlaca());
    }

    @Test
    void atualizarVeiculo() {
        when(repository.findById(ID)).thenReturn(Optional.of(veiculo));
        when(repository.save(any(Veiculo.class))).thenReturn(veiculo);
        when(modelMapper.map(any(Veiculo.class), eq(VeiculoDTO.class))).thenReturn(veiculoDTO);

        VeiculoDTO result = service.atualizarVeiculo(ID, veiculoDTO);
        Mockito.verify(repository, Mockito.times(1)).save(any(Veiculo.class));
        assertEquals(veiculo.getPlaca(), result.getPlaca());
    }

}