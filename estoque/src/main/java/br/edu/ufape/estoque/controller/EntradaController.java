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

import br.edu.ufape.estoque.controller.dto.request.EntradaRequest;
import br.edu.ufape.estoque.controller.dto.response.EntradaResponse;
import br.edu.ufape.estoque.facade.Facade;

@RestController
@RequestMapping("/api/entrada")
public class EntradaController {

	@Autowired
	private Facade facade;

	@PostMapping("/")
	public ResponseEntity<EntradaResponse> createEntrada(@RequestParam EntradaRequest entrada) {
		EntradaResponse entradaR = new EntradaResponse(facade.createEntrada(entrada.toEntity()));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entradaR.getId())
				.toUri();
		return ResponseEntity.created(uri).body(entradaR);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateEntrada(@RequestParam EntradaRequest entrada) {
		facade.updateEntrada(entrada.toEntity());
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEntrada(@PathVariable long id) {
		facade.deleteEntradaById(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<EntradaResponse> findEntradaById(@PathVariable long id) {
		try {
			return ResponseEntity.ok().body(new EntradaResponse(facade.findEntradaById(id)));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/")
	public ResponseEntity<List<EntradaResponse>> listEntrada() {
		return ResponseEntity.ok().body(facade.findAllEntrada().stream().map(x -> new EntradaResponse(x)).toList());
	}
}
