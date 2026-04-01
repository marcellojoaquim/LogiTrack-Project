package br.com.logitrack.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class DashboardKmTotalDTO {

    private Integer veiculoId;
    private String placa;
    private String modelo;
    private BigDecimal kmAcumulada;

    public DashboardKmTotalDTO(Integer veiculoId, String placa, String modelo, BigDecimal kmAcumulada) {
        this.veiculoId = veiculoId;
        this.placa = placa;
        this.modelo = modelo;
        this.kmAcumulada = kmAcumulada;
    }

}
