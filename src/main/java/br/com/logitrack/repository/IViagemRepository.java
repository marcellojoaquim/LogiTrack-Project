package br.com.logitrack.repository;

import br.com.logitrack.model.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IViagemRepository extends JpaRepository<Viagem, Integer> {
}
