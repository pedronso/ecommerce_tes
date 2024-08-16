package br.edu.ufape.estoque.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.estoque.model.Entrada;
import br.edu.ufape.estoque.model.Produto;
import br.edu.ufape.estoque.model.Saida;
import br.edu.ufape.estoque.service.interfaces.IEntradaService;
import br.edu.ufape.estoque.service.interfaces.IProdutoService;
import br.edu.ufape.estoque.service.interfaces.ISaidaService;

@Service
public class Facade {

	@Autowired
	private IEntradaService entradaService;

	public Entrada createEntrada(Entrada entrada) {
		return entradaService.save(entrada);
	}

	public Entrada updateEntrada(Entrada entrada) {
		return entradaService.update(entrada);
	}

	public Entrada findEntradaById(Long id) {
		return entradaService.findById(id);
	}

	public List<Entrada> findAllEntrada() {
		return entradaService.findAll();
	}

	public void deleteEntradaById(Long id) {
		entradaService.deleteById(id);
	}

	@Autowired
	private ISaidaService saidaService;

	public Saida createSaida(Saida saida) {
		return saidaService.save(saida);
	}

	public Saida updateSaida(Saida saida) {
		return saidaService.update(saida);
	}

	public Saida findSaidaById(Long id) {
		return saidaService.findById(id);
	}

	public List<Saida> findAllSaida() {
		return saidaService.findAll();
	}

	public void deleteSaidaById(Long id) {
		saidaService.deleteById(id);
	}

	@Autowired
	private IProdutoService produtoService;

	public Produto createProduto(Produto produto) {
		return produtoService.save(produto);
	}

	public Produto updateProduto(Produto produto) {
		return produtoService.update(produto);
	}

	public Produto findProdutoById(Long id) {
		return produtoService.findById(id);
	}

	public List<Produto> findAllProduto() {
		return produtoService.findAll();
	}

	public void deleteProdutoById(Long id) {
		produtoService.deleteById(id);
	}
}
