package br.edu.ufape.catalogo.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.edu.ufape.catalogo.model.Categoria;


public interface ICategoriaService {
    
    Categoria save(Categoria categoria);
    Optional<Categoria> findById(Long id);
    List<Categoria> findAll();
    void deleteById(Long id);

}
