package br.edu.ufape.catalogo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ufape.catalogo.model.Categoria;
import br.edu.ufape.catalogo.service.interfaces.ICategoriaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    private final ICategoriaService categoriaService;

    @Autowired
    public CategoriaController(ICategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public List<Categoria> getAllCategorias() {
        return categoriaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaService.findById(id);
        return categoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Categoria createCategoria(@RequestBody Categoria categoria) {
        return categoriaService.save(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @RequestBody Categoria categoria) {
        Optional<Categoria> existingCategoria = categoriaService.findById(id);
        if (existingCategoria.isPresent()) {
            categoria.setId(id);
            Categoria updatedCategoria = categoriaService.save(categoria);
            return ResponseEntity.ok(updatedCategoria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
