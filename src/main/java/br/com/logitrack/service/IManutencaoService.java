package br.com.logitrack.service;

import br.com.logitrack.model.dto.DashboardCronogramaManutencaoDTO;
import br.com.logitrack.model.dto.DashboardKmTotalDTO;


import java.math.BigDecimal;
import java.util.List;

public interface IManutencaoService {

    List<DashboardCronogramaManutencaoDTO> buscaProxManutencoes();
    DashboardKmTotalDTO findVeiculoMaxKm();
    BigDecimal projecaoFinanceiraMesAtual();
}
