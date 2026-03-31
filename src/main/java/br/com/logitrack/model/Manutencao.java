package br.com.logitrack.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

/*

id SERIAL PRIMARY KEY,
    veiculo_id INTEGER REFERENCES veiculos(id) ON DELETE CASCADE,
data_inicio DATE NOT NULL,
data_finalizacao DATE,
tipo_servico VARCHAR(100),
custo_estimado DECIMAL(10,2),
Status VARCHAR(20) DEFAULT 'PENDENTE' -- PENDENTE, EM_REALIZACAO, CONCLUIDA
 */

@Entity
@Table(name = "manutencoes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Manutencao {

    public enum Status {
        PENDENTE,
        EM_REALIZACAO,
        CONCLUIDA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Instant dataInicio;

    @Column(nullable = false)
    private Instant dataFim;

    @Column(length = 100, nullable = false)
    private String tipoServico;

    @Column(name = "custo_estimado", precision = 10, scale = 2, nullable = false)
    private BigDecimal custoEstimado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status = Status.PENDENTE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;
}
