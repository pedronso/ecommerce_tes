package br.edu.ufape.estoque.service.interfaces;

import java.util.List;

import br.edu.ufape.estoque.model.Entrada;

public interface IEntradaService {
	Entrada save(Entrada entrada);

	Entrada update(Entrada entrada);

	Entrada findById(Long id);

	List<Entrada> findAll();

	void deleteById(Long id);
}
