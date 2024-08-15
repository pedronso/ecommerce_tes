package br.edu.ufape.catalogo.service.interfaces;

import java.util.List;


import br.edu.ufape.catalogo.dto.CategoriaRequest;
import br.edu.ufape.catalogo.dto.CategoriaResponse;
import br.edu.ufape.catalogo.exceptions.NotFoundException;


public interface ICategoriaService {
    
    CategoriaResponse save(CategoriaRequest categoria) throws NotFoundException;
    CategoriaResponse findById(Long id) throws NotFoundException;
    List<CategoriaResponse> findAll();
    void deleteById(Long id);

}
