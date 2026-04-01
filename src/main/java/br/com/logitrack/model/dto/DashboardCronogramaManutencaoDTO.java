package br.com.logitrack.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
@Builder
@NoArgsConstructor
public class DashboardCronogramaManutencaoDTO {

    private Integer id;
    private String placaVeiculo;
    private String modeloVeiculo;
    private String tipoServico;
    private Instant dataInicio;
    private String status;
}
