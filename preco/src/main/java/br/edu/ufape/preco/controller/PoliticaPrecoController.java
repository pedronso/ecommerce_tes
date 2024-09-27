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

import br.edu.ufape.preco.exceptions.NotFoundException;
import br.edu.ufape.preco.model.PoliticaPreco;
import br.edu.ufape.preco.service.interfaces.IPoliticaPrecoService;

@RestController
@RequestMapping("/api/preco/politica_preco")
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
	
	public ResponseEntity<PoliticaPreco> getPoliticaPrecoById(@PathVariable Long id) throws NotFoundException {
        PoliticaPreco politicaPreco = politicaPrecoService.findById(id);
        return ResponseEntity.ok(politicaPreco);
	}
	
    @PostMapping
    public PoliticaPreco createPoliticaPreco(@RequestBody PoliticaPreco politicaPreco) {
        return politicaPrecoService.save(politicaPreco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PoliticaPreco> updatePoliticaPreco(@PathVariable Long id, @RequestBody PoliticaPreco politicaPreco) throws NotFoundException {
        PoliticaPreco existingProduto = politicaPrecoService.findById(id);
        politicaPreco.setId(id);
        PoliticaPreco updatedPoliticaPreco = politicaPrecoService.save(politicaPreco);
        return ResponseEntity.ok(updatedPoliticaPreco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) throws NotFoundException {
    	politicaPrecoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/produto/{id}")
    public ResponseEntity<PoliticaPreco> connectWithProduto(@PathVariable Long id, @RequestBody long produto_id) throws NotFoundException {
        PoliticaPreco existingProduto = politicaPrecoService.findById(id);
        existingProduto.setId(id);
        PoliticaPreco updatedPoliticaPreco = politicaPrecoService.save(existingProduto);
        return ResponseEntity.ok(updatedPoliticaPreco);
    }
}
