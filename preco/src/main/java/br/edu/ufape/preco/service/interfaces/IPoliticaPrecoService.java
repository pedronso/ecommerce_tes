package br.edu.ufape.preco.service.interfaces;

import br.edu.ufape.preco.model.PoliticaPreco;
import br.edu.ufape.preco.exceptions.NotFoundException;

import java.util.List;

public interface IPoliticaPrecoService {
    
    PoliticaPreco save(PoliticaPreco politicaPreco);

    PoliticaPreco findById(Long id) throws NotFoundException;

    List<PoliticaPreco> findAll();

    void deleteById(Long id) throws NotFoundException;
}
