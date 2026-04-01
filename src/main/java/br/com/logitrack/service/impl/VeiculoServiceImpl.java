package br.com.logitrack.service.impl;

import br.com.logitrack.exception.BusinessException;
import br.com.logitrack.model.Veiculo;
import br.com.logitrack.model.dto.VeiculoDTO;
import br.com.logitrack.repository.IVeiculoRepository;
import br.com.logitrack.service.IVeiculoService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VeiculoServiceImpl implements IVeiculoService {

    private final IVeiculoRepository veiculoRepository;
    private final ModelMapper modelMapper;

    public VeiculoServiceImpl(IVeiculoRepository veiculoRepository, ModelMapper modelMapper) {
        this.veiculoRepository = veiculoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VeiculoDTO cadastrarVeiculo(VeiculoDTO veiculoDTO) {
        if(veiculoRepository.verifyVeiculoByPlaca(veiculoDTO.getPlaca())) {
            throw new BusinessException("Veiculo já existe");
        }
        Veiculo veiculo = new Veiculo();
        toVeiculoFromDTO(veiculo, veiculoDTO);
        veiculoRepository.save(veiculo);

        return modelMapper.map(veiculo, VeiculoDTO.class);
    }

    @Override
    public VeiculoDTO atualizarVeiculo(Integer id, VeiculoDTO veiculoDTO) {
        if(veiculoRepository.findById(id).isEmpty()) {
            throw new BusinessException("Veiculo não encontrado");
        }
        Veiculo veiculo = new Veiculo();
        toVeiculoFromDTO(veiculo, veiculoDTO);
        veiculoRepository.save(veiculo);

        return modelMapper.map(veiculo, VeiculoDTO.class);
    }

    @Override
    public Page<VeiculoDTO> listarVeiculos(Pageable pageable) {

        List<VeiculoDTO> veiculoDTOList = veiculoRepository.findAll(pageable).stream().map(
                veiculo -> modelMapper.map(veiculo, VeiculoDTO.class)
        ).toList();

        return new PageImpl<>(veiculoDTOList, pageable, veiculoDTOList.size());
    }

    @Override
    public List<VeiculoDTO> listarVeiculosParaSelect() {
        List<VeiculoDTO> allByOrderByPlacaAsc = veiculoRepository.findAllByOrderByPlacaAsc();
        if(allByOrderByPlacaAsc.isEmpty()) {
            return new ArrayList<>();
        }
        return allByOrderByPlacaAsc;
    }

    @Override
    public VeiculoDTO buscarVeiculoPorPlaca(String placa) {
        Optional<Veiculo> veiculo = veiculoRepository.findByPlaca(placa);
        return modelMapper.map(veiculo, VeiculoDTO.class);
    }

    @Override
    public VeiculoDTO buscarVeiculoPorId(Integer id) {
        Optional<Veiculo> optionalVeiculo = veiculoRepository.findByIdentificador(id);
        return modelMapper.map(optionalVeiculo, VeiculoDTO.class);
    }

    @Override
    public boolean existsById(Integer idVeiculo) {
        return veiculoRepository.existsById(idVeiculo);
    }


    private void toVeiculoFromDTO(Veiculo veiculo, VeiculoDTO dto) {
        veiculo.setPlaca(dto.getPlaca());
        veiculo.setModelo(dto.getModelo());
        veiculo.setModelo(dto.getModelo());
    }
}
