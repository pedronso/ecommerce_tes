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

import br.edu.ufape.estoque.controller.dto.request.EntradaRequest;
import br.edu.ufape.estoque.controller.dto.response.EntradaResponse;
import br.edu.ufape.estoque.facade.Facade;

@RestController
@RequestMapping("/api/entrada")
public class EntradaController {

	@Autowired
	private Facade facade;

	@PostMapping("/")
	public EntradaResponse createEntrada(@RequestParam EntradaRequest entrada) {
		return new EntradaResponse(facade.createEntrada(entrada.toEntity()));
	}

	@PutMapping("/{id}")
	public EntradaResponse updateEntrada(@RequestParam EntradaRequest entrada) {
		return new EntradaResponse(facade.updateEntrada(entrada.toEntity()));
	}

	@DeleteMapping("/{id}")
	public void deleteEntrada(@PathVariable long id) {
		facade.deleteEntradaById(id);
	}

	@GetMapping("/{id}")
	public EntradaResponse findEntradaById(@PathVariable long id) {
		return new EntradaResponse(facade.findEntradaById(id));
	}

	@GetMapping("/")
	public List<EntradaResponse> listEntrada() {
		return facade.findAllEntrada().stream().map(x -> new EntradaResponse(x)).toList();
	}
}
