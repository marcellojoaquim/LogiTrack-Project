package br.com.logitrack.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViagemDTO {

    @NotNull(message = "A data de saída é obrigatória")
    private Instant dataSaida;

    private Instant dataChegada;

    @NotNull(message = "A origem é obrigatória")
    private String origem;

    @NotNull(message = "O destino é obrigatório")
    private String destino;

    private BigDecimal kmPercorrida;

    @NotNull(message = "Id do veículo é obrigatório")
    private Integer idVeiculo;
}
