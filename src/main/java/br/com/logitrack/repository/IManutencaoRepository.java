package br.com.logitrack.repository;

import br.com.logitrack.model.Manutencao;
import br.com.logitrack.model.dto.DashboardCronogramaManutencaoDTO;
import br.com.logitrack.model.dto.DashboardKmTotalDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IManutencaoRepository extends JpaRepository<Manutencao, Long> {

    @Query(value = "SELECT * FROM manutencoes WHERE status = 'PENDENTE' AND data_inicio >= CURRENT_DATE " +
            "ORDER BY data_inicio ASC LIMIT 5", nativeQuery = true)
    List<DashboardCronogramaManutencaoDTO> bucarProxManutensoes();

    @Query(value = "SELECT ve.id, ve.placa, SUM(vi.km_percorrida) as distancia_total " +
            "FROM veiculos ve INNER JOIN viagens vi ON ve.id = vi.veiculo_id " +
            "GROUP BY ve.id, ve.placa ORDER BY distancia_total DESC LIMIT 1", nativeQuery = true)
    Object findVeiculoMaxKm();

    @Query(value = "SELECT COALESCE(SUM(custo_estimado), 0) FROM manutencoes " +
            "WHERE EXTRACT(MONTH FROM data_inicio) = EXTRACT(MONTH FROM CURRENT_DATE) " +
            "AND EXTRACT(YEAR FROM data_inicio) = EXTRACT(YEAR FROM CURRENT_DATE)", nativeQuery = true)
    BigDecimal projecaoFinanceiraMesAtual();
}
