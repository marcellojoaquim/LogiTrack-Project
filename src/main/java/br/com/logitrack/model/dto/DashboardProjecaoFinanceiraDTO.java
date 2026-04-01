package br.com.logitrack.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class DashboardProjecaoFinanceiraDTO {

    private BigDecimal custoTotalEstimado;
    private String mesReferencia;
    private Integer quantidadeManutencoes;

    public DashboardProjecaoFinanceiraDTO(BigDecimal custoTotal, String mes, Integer qtd) {
        this.custoTotalEstimado = custoTotal;
        this.mesReferencia = mes;
        this.quantidadeManutencoes = qtd;
    }
}
