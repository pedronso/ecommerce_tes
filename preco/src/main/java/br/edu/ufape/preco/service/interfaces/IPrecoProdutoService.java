package br.edu.ufape.preco.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.edu.ufape.preco.model.PrecoProduto;

public interface IPrecoProdutoService {
    PrecoProduto save(PrecoProduto precoProduto);
    Optional<PrecoProduto> findById(Long id);
    List<PrecoProduto> findAll();
    void deleteById(Long id);
	Optional<String> findPoliticaDescricaoById(Long id);
}
