package br.com.logitrack.model.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class DashboardKmTotalDTO {

    private String placa;
    private String modelo;
    private Double kmAcumulada;

    public DashboardKmTotalDTO( String placa, String modelo, Double kmAcumulada) {
        this.placa = placa;
        this.modelo = modelo;
        this.kmAcumulada = kmAcumulada;
    }

}
