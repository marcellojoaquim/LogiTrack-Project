package br.com.logitrack.repository;

import br.com.logitrack.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IVeiculoRepository extends JpaRepository<Veiculo, Integer> {

    @Query(value = "SELECT placa, modelo FROM veiculos WHERE placa = :placa", nativeQuery = true)
    boolean verifyVeiculoByPlaca(@Param("placa") String placa);

    Optional<Veiculo> findById(Integer id);
}
