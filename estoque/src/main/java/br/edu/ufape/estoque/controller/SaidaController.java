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

import br.edu.ufape.estoque.controller.dto.request.SaidaRequest;
import br.edu.ufape.estoque.controller.dto.response.SaidaResponse;
import br.edu.ufape.estoque.facade.Facade;

@RestController
@RequestMapping("/api/saida")
public class SaidaController {
	@Autowired
	private Facade facade;

	@PostMapping("/")
	public SaidaResponse createSaida(@RequestParam SaidaRequest produto) {
		return new SaidaResponse(facade.createSaida(produto.toEntity()));
	}

	@PutMapping("/{id}")
	public SaidaResponse updateSaida(@RequestParam SaidaRequest produto) {
		return new SaidaResponse(facade.updateSaida(produto.toEntity()));
	}

	@DeleteMapping("/{id}")
	public void deleteSaida(@PathVariable long id) {
		facade.deleteSaidaById(id);
	}

	@GetMapping("/{id}")
	public SaidaResponse findSaidaById(@PathVariable long id) {
		return new SaidaResponse(facade.findSaidaById(id));
	}

	@GetMapping("/")
	public List<SaidaResponse> listSaida() {
		return facade.findAllSaida().stream().map(x -> new SaidaResponse(x)).toList();
	}
}
