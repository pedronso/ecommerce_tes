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

import br.edu.ufape.estoque.controller.dto.request.SaidaRequest;
import br.edu.ufape.estoque.controller.dto.response.SaidaResponse;
import br.edu.ufape.estoque.facade.Facade;

@RestController
@RequestMapping("/api/saida")
public class SaidaController {
	@Autowired
	private Facade facade;

	@PostMapping("/")
	public ResponseEntity<SaidaResponse> createSaida(@RequestParam SaidaRequest saida) {
		SaidaResponse saidaR = new SaidaResponse(facade.createSaida(saida.toEntity()));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saidaR.getId())
				.toUri();
		return ResponseEntity.created(uri).body(saidaR);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateSaida(@RequestParam SaidaRequest saida) {
		facade.updateSaida(saida.toEntity());
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSaida(@PathVariable long id) {
		facade.deleteSaidaById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<SaidaResponse> findSaidaById(@PathVariable long id) {
		try {
			return ResponseEntity.ok().body(new SaidaResponse(facade.findSaidaById(id)));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<SaidaResponse>> listSaida() {
		return ResponseEntity.ok().body(facade.findAllSaida().stream().map(x -> new SaidaResponse(x)).toList());
	}
}
