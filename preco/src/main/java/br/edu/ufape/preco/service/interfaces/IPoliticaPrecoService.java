package br.edu.ufape.preco.service.interfaces;

import java.util.List;
import java.util.Optional;

import br.edu.ufape.preco.model.PoliticaPreco;

public interface IPoliticaPrecoService {
	
	PoliticaPreco save(PoliticaPreco politica_preco);
    Optional<PoliticaPreco> findById(Long id);
    List<PoliticaPreco> findAll();
    void deleteById(Long id);
 //   public List<PoliticaPreco> findByPoliticaPreco_nome(String nome);
 //   public List<PoliticaPreco> findByPoliticaPreco_desconto(int desconto);

}
