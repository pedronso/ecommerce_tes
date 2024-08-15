package br.edu.ufape.catalogo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ufape.catalogo.dto.CategoriaRequest;
import br.edu.ufape.catalogo.dto.CategoriaResponse;
import br.edu.ufape.catalogo.exceptions.NotFoundException;
import br.edu.ufape.catalogo.service.interfaces.ICategoriaService;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final ICategoriaService categoriaService;

    public CategoriaController(ICategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> createCategoria(@RequestBody CategoriaRequest categoriaRequest) {

        CategoriaRequest categoriaForSave = new CategoriaRequest();
        categoriaForSave.setNome(categoriaRequest.getNome());
        categoriaForSave.setDescricao(categoriaRequest.getDescricao());
        categoriaForSave.setId(null); // Define o ID como null para criação

        try {
            CategoriaResponse savedCategoriaResponse = categoriaService.save(categoriaForSave);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCategoriaResponse);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> getCategoriaById(@PathVariable Long id) {
        try {
            CategoriaResponse categoriaResponse = categoriaService.findById(id);
            return ResponseEntity.ok(categoriaResponse);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> getAllCategorias() {
        List<CategoriaResponse> categorias = categoriaService.findAll();
        return ResponseEntity.ok(categorias);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> updateCategoria(@PathVariable Long id,
            @RequestBody CategoriaRequest categoriaRequest) {
        categoriaRequest.setId(id);

        try {
            CategoriaResponse updatedCategoriaResponse = categoriaService.save(categoriaRequest);
            return ResponseEntity.ok(updatedCategoriaResponse);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
