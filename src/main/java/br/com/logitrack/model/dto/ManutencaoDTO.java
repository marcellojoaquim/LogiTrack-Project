package br.com.logitrack.model.dto;

import br.com.logitrack.model.Manutencao;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ManutencaoDTO {

    @NotNull(message = "Data de inicio é obrigatória")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dataInicio;

    @NotNull(message = "Data final é obrigatória")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dataFim;

    @NotNull(message = "Tipo de serviço é obrigatório")
    private String tipoServico;

    @NotNull(message = "Custo estimado é obrigatória")
    private BigDecimal custoEstimado;

    @NotNull(message = "O Status da manutenção é obrigatória")
    private Manutencao.Status status;

    @NotNull(message = "O Id do veiculo é obrigatório")
    private Integer veiculo;
}
