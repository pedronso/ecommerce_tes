package br.edu.ufape.catalogo.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.edu.ufape.catalogo.model.Produto;

public interface IProdutoService {

    Produto save(Produto produto);
    Optional<Produto> findById(Long id);
    List<Produto> findAll();
    void deleteById(Long id);

}
