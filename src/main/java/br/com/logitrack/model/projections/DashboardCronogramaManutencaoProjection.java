package br.com.logitrack.model.projections;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDateTime;

public interface DashboardCronogramaManutencaoProjection {

    Long getId();
    String getPlacaVeiculo();
    String getModeloVeiculo();
    String getTipoServico();

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime getDataInicio();

    String getStatus();
}
