package br.com.logitrack.service;

import br.com.logitrack.model.dto.VeiculoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVeiculoService {

    VeiculoDTO cadastrarVeiculo(VeiculoDTO veiculoDTO);
    VeiculoDTO atualizarVeiculo(Long id, VeiculoDTO veiculoDTO);
    Page<VeiculoDTO> listarVeiculos(Pageable pageable);
    List<VeiculoDTO> listarVeiculosParaSelect();
    VeiculoDTO buscarVeiculoPorPlaca(String placa);
    VeiculoDTO buscarVeiculoPorId(Long id);
    boolean existsById(Long idVeiculo);
}
