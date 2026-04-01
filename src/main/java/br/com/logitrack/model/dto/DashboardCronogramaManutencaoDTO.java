package br.com.logitrack.model.dto;

import lombok.*;

import java.time.Instant;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardCronogramaManutencaoDTO {

    private Integer id;
    private String placaVeiculo;
    private String modeloVeiculo;
    private String tipoServico;
    private Instant dataInicio;
    private String status;
}
