package br.edu.ufape.estoque.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.estoque.model.Saida;
import br.edu.ufape.estoque.repository.SaidaRepository;
import br.edu.ufape.estoque.service.interfaces.ISaidaService;

@Service
public class SaidaServiceImpl implements ISaidaService {
	@Autowired
	private SaidaRepository repository;

	public Saida save(Saida entrada) {
		return repository.save(entrada);
	}

	public Saida update(Saida entrada) {
		return repository.save(entrada);
	}

	public Saida findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Saída de id=" + id + " não encontrada"));
	}

	public List<Saida> findAll() {
		return repository.findAll();
	}

	public void deleteById(Long id) {
		repository.delete(findById(id));
	}
}
