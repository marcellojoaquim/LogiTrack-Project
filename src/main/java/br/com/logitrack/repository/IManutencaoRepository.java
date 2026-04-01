package br.com.logitrack.repository;

import br.com.logitrack.model.Manutencao;
import br.com.logitrack.model.dto.VeiculoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface IManutencaoRepository extends JpaRepository<Manutencao, Integer> {

    @Query(value = "SELECT * FROM manutensoes WHERE status = 'PEDENTE' AND data_inicio >= CIRRENT_TIMESTAMP " +
            "ORDER BY data_inicio ASC LIMIT 5", nativeQuery = true)
    List<Manutencao> bucarProxManutensoes();

    @Query(value = "SELECT ve.id, ve.placa, SUM(vi.km_percorida) as distancia_total " +
            "FROM veiculos ve INNER JOIN viagens vi ON ve.id = vi.veiculo_id " +
            "GROUP BY ve.id, ve.placa ORDER BY distancia_total DESC LIMIT 1", nativeQuery = true)
    VeiculoDTO findVeiculoMaxKm();

    @Query(value = "SELECT COALESCE(SUM(custo_estimado), 0) FROM manutensoes " +
            "WHERE EXTRACT(MONTH FROM data_inicio) = EXTRACT(MONTH FROM CURRENT_DATE) " +
            "AND EXTRACT(YEAR FROM data_inicio) = EXTRACT(YEAR FROM CURRENT_DATE)", nativeQuery = true)
    BigDecimal projecaoFinanceiraMesAtual();
}
