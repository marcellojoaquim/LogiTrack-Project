package br.com.logitrack.repository;

import br.com.logitrack.model.Viagem;
import br.com.logitrack.model.dto.ViagemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IViagemRepository extends JpaRepository<Viagem, Integer> {

    @Query(value = "SELECT FROM viagens WHERE veiculo = :veiculo", nativeQuery = true)
    ViagemDTO findByIdVeiculo(@Param("veiculo") Integer veiculo);


}
