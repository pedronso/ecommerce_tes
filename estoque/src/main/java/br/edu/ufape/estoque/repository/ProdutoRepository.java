package br.edu.ufape.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.estoque.model.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
