package br.edu.ufape.estoque.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.estoque.model.Produto;
import br.edu.ufape.estoque.repository.ProdutoRepository;
import br.edu.ufape.estoque.service.interfaces.IProdutoService;

@Service
public class PrudutoServiceImpl implements IProdutoService {
	@Autowired
	private ProdutoRepository repository;

	public Produto save(Produto entrada) {
		return repository.save(entrada);
	}

	public Produto update(Produto entrada) {
		return repository.save(entrada);
	}

	public Produto findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Produto de id=" + id + " não encontrado"));
	}

	public List<Produto> findAll() {
		return repository.findAll();
	}

	public void deleteById(Long id) {
		repository.delete(findById(id));
	}
}
