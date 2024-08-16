package br.edu.ufape.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.estoque.controller.dto.request.ProdutoRequest;
import br.edu.ufape.estoque.controller.dto.response.ProdutoResponse;
import br.edu.ufape.estoque.facade.Facade;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
	@Autowired
	private Facade facade;

	@PostMapping("/")
	public ProdutoResponse createProduto(@RequestParam ProdutoRequest produto) {
		return new ProdutoResponse(facade.createProduto(produto.toEntity()));
	}

	@PutMapping("/{id}")
	public ProdutoResponse updateProduto(@RequestParam ProdutoRequest produto) {
		return new ProdutoResponse(facade.updateProduto(produto.toEntity()));
	}

	@DeleteMapping("/{id}")
	public void deleteProduto(@PathVariable long id) {
		facade.deleteProdutoById(id);
	}

	@GetMapping("/{id}")
	public ProdutoResponse findProdutoById(@PathVariable long id) {
		return new ProdutoResponse(facade.findProdutoById(id));
	}

	@GetMapping("/")
	public List<ProdutoResponse> listProduto() {
		return facade.findAllProduto().stream().map(x -> new ProdutoResponse(x)).toList();
	}
}
