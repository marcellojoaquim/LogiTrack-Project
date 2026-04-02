package br.com.logitrack.repository;

import br.com.logitrack.model.Manutencao;
import br.com.logitrack.model.projections.DashboardCronogramaManutencaoProjection;
import br.com.logitrack.model.projections.DashboardKmProjection;
import br.com.logitrack.model.projections.VeiculoKmProjection;
import br.com.logitrack.model.dto.DashboardCronogramaManutencaoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IManutencaoRepository extends JpaRepository<Manutencao, Long> {

    @Query(value = "SELECT m.id as id, v.placa as placaVeiculo, v.modelo as modeloVeiculo, " +
            "m.data_inicio as dataInicio, m.tipo_servico as tipoServico, m.status as status " +
            "FROM manutencoes m " +
            "INNER JOIN veiculos v ON m.veiculo_id = v.id " +
            "WHERE m.status = 'PENDENTE' AND m.data_inicio >= CURRENT_DATE " +
            "ORDER BY m.data_inicio ASC LIMIT 5", nativeQuery = true)
    List<DashboardCronogramaManutencaoProjection> bucarProxManutensoes();

    @Query(value = "SELECT ve.id, ve.placa, SUM(vi.km_percorrida) as distancia_total " +
            "FROM veiculos ve INNER JOIN viagens vi ON ve.id = vi.veiculo_id " +
            "GROUP BY ve.id, ve.placa ORDER BY distancia_total DESC LIMIT 1", nativeQuery = true)
    DashboardKmProjection findVeiculoMaxKm();

    @Query("SELECT COALESCE(SUM(m.custoEstimado), 0) FROM Manutencao m " +
            "WHERE MONTH(m.dataInicio) = MONTH(CURRENT_DATE) " +
            "AND YEAR(m.dataInicio) = YEAR(CURRENT_DATE)")
    BigDecimal projecaoFinanceiraMesAtual();
}
