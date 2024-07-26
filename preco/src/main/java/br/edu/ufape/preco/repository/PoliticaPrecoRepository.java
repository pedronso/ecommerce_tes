package br.edu.ufape.preco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufape.preco.model.PoliticaPreco;

@Repository
public interface PoliticaPrecoRepository extends JpaRepository<PoliticaPreco, Long>{
	List<PoliticaPreco> findByPoliticaPreco_nome(String nome);
	List<PoliticaPreco> findByPoliticaPreco_desconto(int desconto);

}