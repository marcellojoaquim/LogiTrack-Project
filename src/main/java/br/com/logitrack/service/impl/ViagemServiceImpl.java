package br.com.logitrack.service.impl;

import br.com.logitrack.exception.BusinessException;
import br.com.logitrack.model.Viagem;
import br.com.logitrack.model.dto.VeiculoDTO;
import br.com.logitrack.model.dto.ViagemDTO;
import br.com.logitrack.repository.IViagemRepository;
import br.com.logitrack.service.IViagemService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class ViagemServiceImpl implements IViagemService {

    private final VeiculoServiceImpl veiculoService;
    private final IViagemRepository viagemRepository;
    private final ModelMapper modelMapper;

    public ViagemServiceImpl(VeiculoServiceImpl veiculoService, IViagemRepository viagemRepository, ModelMapper modelMapper) {
        this.veiculoService = veiculoService;
        this.viagemRepository = viagemRepository;
        this.modelMapper = modelMapper;
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
    public ViagemDTO atualizar(Long id, ViagemDTO viagemDTO) {
        Optional<Viagem> optional = viagemRepository.findById(id);
        if(optional.isEmpty()){
            throw new BusinessException("Viagem não encontrada para o id informado.");
        }
        return viagemRepository.save(viagemDTO);
    }

    public Optional<ViagemDTO> findById(Long id) {
        Optional<Viagem> optional = viagemRepository.findById(id);
        ViagemDTO map = modelMapper.map(optional, ViagemDTO.class);
        return Optional.of(map);
    }

    @Override
    public ViagemDTO encerrar(Long id, LocalDateTime dataChegada) {
        viagemRepository.findById(id)
                .map(v -> {
                    v.setDataChegada(LocalDateTime.now());
                    return viagemRepository.save(v);
                }).orElseThrow(() -> new BusinessException("Viagem não encontada para o id informado"));
        return null;
    }

    @Override
    public List<ViagemDTO> findAllByIdVeiculo(Long veiculo) {
        List<ViagemDTO> list = viagemRepository.findAllByIdVeiculo(veiculo);
        if (list.isEmpty()) {
            throw new BusinessException("Viagem não encontrada para o id do veiculo");
        }

        return list;
    }

    @Override
    public BigDecimal kmTotal() {
        return viagemRepository.kmTotal();
    }

    @Override
    public BigDecimal kmTotalPorVeiculo(Long veiculo) {
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
