package br.edu.ufape.preco.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ufape.preco.model.PrecoProduto;
import br.edu.ufape.preco.service.interfaces.IPrecoProdutoService;

@RestController
@RequestMapping("/api/preco_produto")
public class PrecoProdutoController {

    private final IPrecoProdutoService precoProdutoService;

    @Autowired
    public PrecoProdutoController(IPrecoProdutoService precoProdutoService) {
        this.precoProdutoService = precoProdutoService;
    }

    @GetMapping
    public List<PrecoProduto> getAllPrecoProdutos() {
        return precoProdutoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrecoProduto> getPrecoProdutoById(@PathVariable Long id) {
        Optional<PrecoProduto> precoProduto = precoProdutoService.findById(id);
        return precoProduto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PrecoProduto createPrecoProduto(@RequestBody PrecoProduto precoProduto) {
        return precoProdutoService.save(precoProduto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrecoProduto(@PathVariable Long id) {
        precoProdutoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/politica")
    public ResponseEntity<String> getPoliticaPrecoDescricaoById(@PathVariable Long id) {
        Optional<String> politicaDescricao = precoProdutoService.findPoliticaDescricaoById(id);
        return politicaDescricao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
