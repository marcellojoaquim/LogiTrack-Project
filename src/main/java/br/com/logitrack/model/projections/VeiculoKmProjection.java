package br.com.logitrack.model.projections;

import java.math.BigDecimal;

public interface VeiculoKmProjection {
    Long getId();
    String getPlaca();
    BigDecimal getDistanciaTotal();
}
