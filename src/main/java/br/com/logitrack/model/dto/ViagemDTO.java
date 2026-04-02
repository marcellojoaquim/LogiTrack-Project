package br.com.logitrack.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViagemDTO {

    public ViagemDTO(LocalDateTime dataSaida, LocalDateTime dataChegada, Long idVeiculo) {
        this.dataSaida = dataSaida;
        this.dataChegada = dataChegada;
        this.idVeiculo = idVeiculo;
    }

    @NotNull(message = "A data de saída é obrigatória")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dataSaida;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dataChegada;

    @NotNull(message = "A origem é obrigatória")
    private String origem;

    @NotNull(message = "O destino é obrigatório")
    private String destino;

    private BigDecimal kmPercorrida;

    @NotNull(message = "Id do veículo é obrigatório")
    private Long idVeiculo;
}
