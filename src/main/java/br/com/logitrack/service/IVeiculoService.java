package br.com.logitrack.service;

import br.com.logitrack.model.dto.VeiculoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IVeiculoService {

    VeiculoDTO cadastrarVeiculo(VeiculoDTO veiculoDTO);
    VeiculoDTO atualizarVeiculo(Integer id, VeiculoDTO veiculoDTO);
    Page<VeiculoDTO> listarVeiculos(Pageable pageable);
}
