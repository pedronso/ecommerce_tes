package br.edu.ufape.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufape.estoque.model.Entrada;

public interface EntradaRepository extends JpaRepository<Entrada, Long> {

}
