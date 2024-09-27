package br.edu.ufape.preco.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.ufape.preco.exceptions.NotFoundException;
import br.edu.ufape.preco.model.PoliticaPreco;
import br.edu.ufape.preco.repository.PoliticaPrecoRepository;
import br.edu.ufape.preco.service.interfaces.IPoliticaPrecoService;

@Service
public class PoliticaPrecoServiceImpl implements IPoliticaPrecoService {

	private final PoliticaPrecoRepository politicaPrecoRepository;

    public PoliticaPrecoServiceImpl(PoliticaPrecoRepository politicaPrecoRepository) {
        this.politicaPrecoRepository = politicaPrecoRepository;
    }
	
	@Override
	public PoliticaPreco save(PoliticaPreco politicaPreco) {
		return politicaPrecoRepository.save(politicaPreco);
	}

	@Override
    public PoliticaPreco findById(Long id) throws NotFoundException {
        return politicaPrecoRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Política de Preço não encontrada para o ID: " + id));
    }

    @Override
    public List<PoliticaPreco> findAll() {
        return politicaPrecoRepository.findAll();
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        PoliticaPreco politicaPreco = findById(id); 
        politicaPrecoRepository.deleteById(politicaPreco.getId());
    }
	
//	@Override
//	public List<PoliticaPreco> findByPoliticaPreco_nome(String nome) {
//		return politicaPrecoRepository.findByPoliticaPreco_nome(nome);
//	}
	
//	@Override
//	public List<PoliticaPreco> findByPoliticaPreco_desconto(int desconto) {
//		return politicaPrecoRepository.findByPoliticaPreco_desconto(desconto);
//	}
}
