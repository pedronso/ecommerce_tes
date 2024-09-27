package br.edu.ufape.preco.service.interfaces;

import java.util.List;

import br.edu.ufape.preco.exceptions.NotFoundException;
import br.edu.ufape.preco.model.PrecoProduto;

public interface IPrecoProdutoService {
    PrecoProduto save(PrecoProduto precoProduto);

    PrecoProduto findById(Long id) throws NotFoundException; 

    List<PrecoProduto> findAll();

    void deleteById(Long id) throws NotFoundException; 
}
