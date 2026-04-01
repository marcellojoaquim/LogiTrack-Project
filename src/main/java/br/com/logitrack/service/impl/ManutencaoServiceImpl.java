package br.com.logitrack.service.impl;

import br.com.logitrack.model.dto.DashboardCronogramaManutencaoDTO;
import br.com.logitrack.model.dto.DashboardKmTotalDTO;
import br.com.logitrack.repository.IManutencaoRepository;
import br.com.logitrack.service.IManutencaoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManutencaoServiceImpl implements IManutencaoService {

    private final IManutencaoRepository manutencaoRepository;
    private final ModelMapper modelMapper;

    public ManutencaoServiceImpl(IManutencaoRepository manutencaoRepository, ModelMapper modelMapper) {
        this.manutencaoRepository = manutencaoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DashboardCronogramaManutencaoDTO> buscaProxManutencoes() {
        List<DashboardCronogramaManutencaoDTO> dashboardCronogramaManutencaoDTOS = manutencaoRepository.bucarProxManutensoes();
        if(dashboardCronogramaManutencaoDTOS.isEmpty()) {
            return new ArrayList<>();
        }
        return dashboardCronogramaManutencaoDTOS;
    }

    @Override
    public DashboardKmTotalDTO findVeiculoMaxKm() {
        Object resultado = manutencaoRepository.findVeiculoMaxKm();
        if (resultado == null) {
            return null;
        }

        Object[] colunas = (Object[]) resultado;

        String placa = (String) colunas[0];
        String modelo = (String) colunas[1];
        Double kmTotal = 0.0;

        if (colunas[2] != null) {
            kmTotal = ((Number) colunas[2]).doubleValue();
        }

        return new DashboardKmTotalDTO(placa, modelo, kmTotal);
    }

    @Override
    public BigDecimal projecaoFinanceiraMesAtual() {
        return manutencaoRepository.projecaoFinanceiraMesAtual();
    }
}
