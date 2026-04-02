package br.com.logitrack.service.impl;

import br.com.logitrack.model.dto.DashboardCronogramaManutencaoDTO;
import br.com.logitrack.model.dto.DashboardKmTotalDTO;
import br.com.logitrack.model.projections.DashboardCronogramaManutencaoProjection;
import br.com.logitrack.model.projections.DashboardKmProjection;
import br.com.logitrack.model.projections.VeiculoKmProjection;
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
        List<DashboardCronogramaManutencaoProjection> projections = manutencaoRepository.bucarProxManutensoes();
        if (projections.isEmpty()) {
            return new ArrayList<>();
        }

        return projections.stream()
                .map(p -> DashboardCronogramaManutencaoDTO.builder()
                        .id(p.getId())
                        .placaVeiculo(p.getPlacaVeiculo())
                        .modeloVeiculo(p.getModeloVeiculo())
                        .tipoServico(p.getTipoServico())
                        .dataInicio(p.getDataInicio())
                        .status(p.getStatus())
                        .build()
                )
                .toList();
    }

    @Override
    public DashboardKmTotalDTO findVeiculoMaxKm() {
        DashboardKmProjection veiculoKmProjection = manutencaoRepository.findVeiculoMaxKm();
        if (veiculoKmProjection == null) {
            return null;
        }

        return new DashboardKmTotalDTO(
                veiculoKmProjection.getPlaca(),
                veiculoKmProjection.getModelo(),
                veiculoKmProjection.getDistanciaTotal()
        );
    }

    @Override
    public BigDecimal projecaoFinanceiraMesAtual() {
        BigDecimal bigDecimal = manutencaoRepository.projecaoFinanceiraMesAtual();

        return bigDecimal;
    }
}
