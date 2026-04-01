package br.com.logitrack.service.impl;

import br.com.logitrack.exception.BusinessException;
import br.com.logitrack.model.dto.VeiculoDTO;
import br.com.logitrack.model.dto.ViagemDTO;
import br.com.logitrack.repository.IViagemRepository;
import br.com.logitrack.service.IViagemService;

import java.math.BigDecimal;

public class ViagemServiceImpl implements IViagemService {

    private final VeiculoServiceImpl veiculoService;
    private final IViagemRepository viagemRepository;

    public ViagemServiceImpl(VeiculoServiceImpl veiculoService, IViagemRepository viagemRepository) {
        this.veiculoService = veiculoService;
        this.viagemRepository = viagemRepository;
    }

    @Override
    public ViagemDTO salvar(ViagemDTO viagemDTO) {
        VeiculoDTO veiculoDTO = veiculoService.buscarVeiculoPorId(viagemDTO.getIdVeiculo());
        if(veiculoDTO == null){
            throw new BusinessException("Veiculo não encontrado");
        }
        return viagemRepository.save(viagemDTO);
    }

    @Override
    public ViagemDTO findByIdVeiculo(Integer idVeiculo) {
        ViagemDTO viagemDTO = viagemRepository.findByIdVeiculo(idVeiculo);
        if (viagemDTO == null) {
            throw new BusinessException("Viagem não encontrada para o id do veiculo");
        }

        return viagemDTO;
    }

    @Override
    public BigDecimal kmTotal() {
        return viagemRepository.kmTotal();
    }

    @Override
    public BigDecimal kmTotalPorVeiculo(Integer veiculo) {
        if(!veiculoService.existsById(veiculo)) {
            throw new BusinessException("Veiculo não encontrado.");
        }
        return viagemRepository.kmTotalPorVeiculo(veiculo);
    }

    @Override
    public Long volumePorCategoria(String tipo) {
        return  viagemRepository.volumePorCategoria(tipo);
    }


}
