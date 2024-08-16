package br.edu.ufape.catalogo.service.interfaces;

import java.util.List;

import br.edu.ufape.catalogo.dto.ProdutoRequest;
import br.edu.ufape.catalogo.dto.ProdutoResponse;
import br.edu.ufape.catalogo.exceptions.NotFoundException;

public interface IProdutoService {

    ProdutoResponse save(ProdutoRequest produto) throws NotFoundException;

    ProdutoResponse findById(Long id) throws NotFoundException;

    List<ProdutoResponse> findAll();

    void deleteById(Long id);

}
