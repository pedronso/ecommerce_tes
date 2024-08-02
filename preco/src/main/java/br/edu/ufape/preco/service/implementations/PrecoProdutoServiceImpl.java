package br.edu.ufape.preco.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.preco.model.PrecoProduto;
import br.edu.ufape.preco.repository.PrecoProdutoRepository;
import br.edu.ufape.preco.service.interfaces.IPrecoProdutoService;

@Service
public class PrecoProdutoServiceImpl implements IPrecoProdutoService {

    private final PrecoProdutoRepository precoProdutoRepository;

    @Autowired
    public PrecoProdutoServiceImpl(PrecoProdutoRepository precoProdutoRepository) {
        this.precoProdutoRepository = precoProdutoRepository;
    }

    @Override
    public PrecoProduto save(PrecoProduto precoProduto) {
        return precoProdutoRepository.save(precoProduto);
    }

    @Override
    public Optional<PrecoProduto> findById(Long id) {
        return precoProdutoRepository.findById(id);
    }

    @Override
    public List<PrecoProduto> findAll() {
        return precoProdutoRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        precoProdutoRepository.deleteById(id);
    }

    public Optional<String> findPoliticaDescricaoById(Long id) {
        Optional<PrecoProduto> precoProduto = precoProdutoRepository.findById(id);
        return precoProduto.map(pp -> pp.getPoliticaPreco().getDescricao());
    }
}
