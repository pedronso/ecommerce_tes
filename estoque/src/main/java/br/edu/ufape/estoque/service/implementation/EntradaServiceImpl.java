package br.edu.ufape.estoque.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.estoque.model.Entrada;
import br.edu.ufape.estoque.repository.EntradaRepository;
import br.edu.ufape.estoque.service.interfaces.IEntradaService;

@Service
public class EntradaServiceImpl implements IEntradaService {

	@Autowired
	private EntradaRepository repository;

	public Entrada save(Entrada entrada) {
		return repository.save(entrada);
	}

	public Entrada update(Entrada entrada) {
		return repository.save(entrada);
	}

	public Entrada findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Entrada de id="+id+" não encontrada"));
	}

	public List<Entrada> findAll() {
		return repository.findAll();
	}

	public void deleteById(Long id) {
		repository.delete(findById(id));
	}

}
