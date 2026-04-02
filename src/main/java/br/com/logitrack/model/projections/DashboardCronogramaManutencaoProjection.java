package br.com.logitrack.model.projections;

import java.time.Instant;

public interface DashboardCronogramaManutencaoProjection {

    Long getId();
    String getPlacaVeiculo();
    String getModeloVeiculo();
    String getTipoServico();
    Instant getDataInicio();
    String getStatus();
}
