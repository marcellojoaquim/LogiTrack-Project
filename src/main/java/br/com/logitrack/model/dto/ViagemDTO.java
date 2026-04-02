package br.com.logitrack.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class ViagemDTO {


    private Long id;

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

    private String placaVeiculo;

    public ViagemDTO(LocalDateTime dataSaida, LocalDateTime dataChegada, Long idVeiculo) {
        this.dataSaida = dataSaida;
        this.dataChegada = dataChegada;
        this.idVeiculo = idVeiculo;
    }

    public ViagemDTO(Long id, LocalDateTime dataSaida, LocalDateTime dataChegada, String origem, String destino, BigDecimal kmPercorrida, Long idVeiculo, String placaVeiculo) {
        this.id = id;
        this.dataSaida = dataSaida;
        this.dataChegada = dataChegada;
        this.origem = origem;
        this.destino = destino;
        this.kmPercorrida = kmPercorrida;
        this.idVeiculo = idVeiculo;
        this.placaVeiculo = placaVeiculo;
    }
}
