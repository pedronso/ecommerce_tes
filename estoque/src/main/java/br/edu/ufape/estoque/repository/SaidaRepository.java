package br.edu.ufape.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufape.estoque.model.Saida;

public interface SaidaRepository extends JpaRepository<Saida, Long> {

}
