package br.edu.ufape.catalogo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ufape.catalogo.dto.ProdutoRequest;
import br.edu.ufape.catalogo.dto.ProdutoResponse;
import br.edu.ufape.catalogo.exceptions.NotFoundException;
import br.edu.ufape.catalogo.service.interfaces.IProdutoService;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final IProdutoService produtoService;

    public ProdutoController(IProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> createProduto(@RequestBody ProdutoRequest produtoRequest) {
        try {
            ProdutoResponse savedProdutoResponse = produtoService.save(produtoRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProdutoResponse);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> getProdutoById(@PathVariable Long id) {
        try {
            ProdutoResponse produtoResponse = produtoService.findById(id);
            return ResponseEntity.ok(produtoResponse);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> getAllProdutos() {
        List<ProdutoResponse> produtos = produtoService.findAll();
        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> updateProduto(@PathVariable Long id,
            @RequestBody ProdutoRequest produtoRequest) {
        produtoRequest.setId(id);

        try {
            ProdutoResponse updatedProdutoResponse = produtoService.save(produtoRequest);
            return ResponseEntity.ok(updatedProdutoResponse);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        produtoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
