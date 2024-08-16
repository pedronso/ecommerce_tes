package br.edu.ufape.estoque.service.interfaces;

import java.util.List;

import br.edu.ufape.estoque.model.Saida;

public interface ISaidaService {
	Saida save(Saida saida);

	Saida update(Saida saida);

	Saida findById(Long id);

	List<Saida> findAll();

	void deleteById(Long id);
}
