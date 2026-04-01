package br.com.logitrack.service;

import br.com.logitrack.model.dto.ViagemDTO;

import java.math.BigDecimal;

public interface IViagemService {

    ViagemDTO salvar(ViagemDTO viagemDTO);
    ViagemDTO findByIdVeiculo(Integer idVeiculo);
    BigDecimal kmTotal();
    BigDecimal kmTotalPorVeiculo(Integer veiculo);
    Long volumePorCategoria(String tipo);


}
