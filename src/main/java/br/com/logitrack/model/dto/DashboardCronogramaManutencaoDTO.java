package br.com.logitrack.model.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardCronogramaManutencaoDTO {

    private Long id;
    private String placaVeiculo;
    private String modeloVeiculo;
    private String tipoServico;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dataInicio;

    private String status;
}
