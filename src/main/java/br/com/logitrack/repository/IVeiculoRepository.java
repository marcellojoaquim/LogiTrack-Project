package br.com.logitrack.repository;

import br.com.logitrack.model.Veiculo;
import br.com.logitrack.model.dto.VeiculoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IVeiculoRepository extends JpaRepository<Veiculo, Long> {


    boolean existsByPlaca(@Param("placa") String placa);

    @Query(value = "SELECT id, placa, modelo, tipo, ano FROM veiculos WHERE id = :id", nativeQuery = true)
    Optional<Veiculo> findByIdentificador(@Param("id") Long id);

    Optional<Veiculo> findByPlaca(String placa);

    @Query("SELECT new br.com.logitrack.model.dto.VeiculoDTO(v.id, v.placa, v.modelo, v.tipo, v.ano) " +
            "FROM Veiculo v ORDER BY v.placa ASC")
    List<VeiculoDTO> findAllByOrderByPlacaAsc();
}
