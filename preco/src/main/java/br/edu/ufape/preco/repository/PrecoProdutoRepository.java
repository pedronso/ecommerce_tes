package br.edu.ufape.preco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.preco.model.PrecoProduto;

@Repository
public interface PrecoProdutoRepository extends JpaRepository<PrecoProduto, Long> {
}
