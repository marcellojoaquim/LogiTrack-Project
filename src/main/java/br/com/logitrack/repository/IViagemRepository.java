package br.com.logitrack.repository;

import br.com.logitrack.model.Viagem;
import br.com.logitrack.model.dto.ViagemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IViagemRepository extends JpaRepository<Viagem, Long> {

    @Query(value = "SELECT * FROM viagens WHERE veiculo_id = :veiculo", nativeQuery = true)
    ViagemDTO findByIdVeiculo(@Param("veiculo") Long veiculo);

    ViagemDTO save(ViagemDTO viagemDTO);

    @Query(value = "SELECT COALESCE(SUM(km_percorrida), 0) FROM viagens", nativeQuery = true)
    BigDecimal kmTotal();

    @Query(value = "SELECT COALESCE(SUM(km_percorrida),0) FROM viagens WHERE veiculo_id = :veiculo", nativeQuery = true)
    BigDecimal kmTotalPorVeiculo(@Param("veiculo") Long veiculo);

    @Query(value = "SELECT COUNT(*) FROM viagens v INNER JOIN veiculos ve ON v.veiculo_id = ve.id WHERE ve.tipo = :tipo",
            nativeQuery = true)
    Long volumePorCategoria(@Param("tipo") String tipo);

    @Query("SELECT new br.com.logitrack.model.dto.ViagemDTO( v.dataSaida, v.dataChegada, v.veiculo.id) " +
            "FROM Viagem v WHERE v.veiculo.id = :veiculo")
    List<ViagemDTO> findAllByIdVeiculo(Long veiculo);
}
