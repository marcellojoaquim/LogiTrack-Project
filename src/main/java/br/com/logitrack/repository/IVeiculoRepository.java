package br.com.logitrack.repository;

import br.com.logitrack.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVeiculoRepository extends JpaRepository<Veiculo, Integer> {
}
