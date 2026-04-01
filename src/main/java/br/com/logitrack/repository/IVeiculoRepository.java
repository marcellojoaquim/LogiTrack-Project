package br.com.logitrack.repository;

import br.com.logitrack.model.Veiculo;
import br.com.logitrack.model.dto.VeiculoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IVeiculoRepository extends JpaRepository<Veiculo, Integer> {

    @Query(value = "SELECT placa, modelo FROM veiculos WHERE placa = :placa", nativeQuery = true)
    boolean verifyVeiculoByPlaca(@Param("placa") String placa);

    @Query(value = "SELECT id, placa, modelo, tipo, ano FROM veiculos WHERE id = :id", nativeQuery = true)
    Optional<Veiculo> findByIdentificador(@Param("id") Integer id);

    @Query(value = "SELECT placa, modelo, tipo, ano FROM veiculos ORDER BY placa ASC", nativeQuery = true)
    List<VeiculoDTO> findAllByOrderByPlacaAsc();
}
