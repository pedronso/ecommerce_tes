package br.edu.ufape.estoque.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.ufape.estoque.controller.dto.request.ProdutoRequest;
import br.edu.ufape.estoque.controller.dto.response.ProdutoResponse;
import br.edu.ufape.estoque.facade.Facade;

@RestController
@RequestMapping("/api/estoque/produto")
public class ProdutoController {
	@Autowired
	private Facade facade;

	@PostMapping("/")
	public ResponseEntity<ProdutoResponse> createProduto(@RequestParam ProdutoRequest produto) {
		ProdutoResponse produtoR = new ProdutoResponse(facade.createProduto(produto.toEntity()));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produtoR.getId())
				.toUri();
		return ResponseEntity.created(uri).body(produtoR);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateProduto(@RequestParam ProdutoRequest produto) {
		facade.updateProduto(produto.toEntity());
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduto(@PathVariable long id) {
		facade.deleteProdutoById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoResponse> findProdutoById(@PathVariable long id) {
		try {
			return ResponseEntity.ok().body(new ProdutoResponse(facade.findProdutoById(id)));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<ProdutoResponse>> listProduto() {
		return ResponseEntity.ok().body(facade.findAllProduto().stream().map(x -> new ProdutoResponse(x)).toList());
	}
}
