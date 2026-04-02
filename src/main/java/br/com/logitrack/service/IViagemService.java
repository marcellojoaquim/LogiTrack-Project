package br.com.logitrack.service;

import br.com.logitrack.model.dto.ViagemDTO;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IViagemService {

    ViagemDTO salvar(ViagemDTO viagemDTO);
    ViagemDTO atualizar(Long id, ViagemDTO viagemDTO);
    Optional<ViagemDTO> findById(Long id);
    ViagemDTO encerrar(Long id, LocalDateTime dataChegada);
    List<ViagemDTO> findAllByIdVeiculo(Long idVeiculo);
    BigDecimal kmTotal();
    BigDecimal kmTotalPorVeiculo(Long veiculo);
    Long volumePorCategoria(String tipo);


}
