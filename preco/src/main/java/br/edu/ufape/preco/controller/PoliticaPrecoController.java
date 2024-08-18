package br.edu.ufape.preco.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufape.preco.model.PoliticaPreco;
import br.edu.ufape.preco.service.interfaces.IPoliticaPrecoService;

@RestController
@RequestMapping("/api/politica_preco")
public class PoliticaPrecoController {

	private final IPoliticaPrecoService politicaPrecoService;
	
	@Autowired
	public PoliticaPrecoController(IPoliticaPrecoService politicaPrecoService) {
		this.politicaPrecoService = politicaPrecoService;
	}
	
	@GetMapping
	public List<PoliticaPreco> getAllPoliticaPreco() {
		return politicaPrecoService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PoliticaPreco> getPoliticaPrecoById(@PathVariable Long id) {
        Optional<PoliticaPreco> politicaPreco = politicaPrecoService.findById(id);
        return politicaPreco.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
    @PostMapping
    public PoliticaPreco createPoliticaPreco(@RequestBody PoliticaPreco politicaPreco) {
        return politicaPrecoService.save(politicaPreco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PoliticaPreco> updatePoliticaPreco(@PathVariable Long id, @RequestBody PoliticaPreco politicaPreco) {
        Optional<PoliticaPreco> existingProduto = politicaPrecoService.findById(id);
        if (existingProduto.isPresent()) {
        	politicaPreco.setId(id);
            PoliticaPreco updatedPoliticaPreco = politicaPrecoService.save(politicaPreco);
            return ResponseEntity.ok(updatedPoliticaPreco);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
    	politicaPrecoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/produto/{id}")
    public ResponseEntity<PoliticaPreco> connectWithProduto(@PathVariable Long id, @RequestBody long produto_id) {
        Optional<PoliticaPreco> existingProduto = politicaPrecoService.findById(id);
        if (existingProduto.isPresent()) {
        	existingProduto.get().setId(id);
            PoliticaPreco updatedPoliticaPreco = politicaPrecoService.save(existingProduto.get());
            return ResponseEntity.ok(updatedPoliticaPreco);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
