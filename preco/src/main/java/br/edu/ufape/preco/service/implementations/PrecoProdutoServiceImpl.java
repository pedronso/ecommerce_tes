package br.edu.ufape.preco.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.preco.exceptions.NotFoundException;
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
    public PrecoProduto findById(Long id) throws NotFoundException {
        return precoProdutoRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Preço do Produto não encontrado para o ID: " + id));
    }

    @Override
    public List<PrecoProduto> findAll() {
        return precoProdutoRepository.findAll();
    }

    @Override
    public void deleteById(Long id) throws NotFoundException {
        PrecoProduto precoProduto = findById(id); // Lança NotFoundException se não encontrar
        precoProdutoRepository.deleteById(precoProduto.getId());
    }


 //   public Optional<String> findPoliticaDescricaoById(Long id) {
 //       Optional<PrecoProduto> precoProduto = precoProdutoRepository.findById(id);
 //       return precoProduto.map(pp -> pp.getPoliticaPreco().getDescricao());
 //   }
}
