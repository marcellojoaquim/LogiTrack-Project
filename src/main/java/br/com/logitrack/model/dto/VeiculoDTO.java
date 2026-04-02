package br.com.logitrack.model.dto;

import br.com.logitrack.model.Veiculo;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VeiculoDTO {

    private Long id;

    @NotNull(message = "Placa é obrigatória")
    private String placa;

    @NotNull(message = "Modelo é obrigatório")
    private String modelo;

    @NotNull(message = "Tipo de veículo é obrigatório")
    private Veiculo.TipoVeiculo tipo;

    @NotNull(message = "O ano do modelo é obrigatório")
    private Integer ano;
}
