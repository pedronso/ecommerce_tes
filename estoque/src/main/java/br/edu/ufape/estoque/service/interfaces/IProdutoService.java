package br.edu.ufape.estoque.service.interfaces;

import java.util.List;

import br.edu.ufape.estoque.model.Produto;

public interface IProdutoService {
	Produto save(Produto produto);

	Produto update(Produto produto);
	
	Produto findById(Long id);

	List<Produto> findAll();

	void deleteById(Long id);
}
