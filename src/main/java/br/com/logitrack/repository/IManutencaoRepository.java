package br.com.logitrack.repository;

import br.com.logitrack.model.Manutencao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IManutencaoRepository extends JpaRepository<Manutencao, Integer> {
}
