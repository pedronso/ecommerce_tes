package br.edu.ufape.catalogo.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.catalogo.model.Produto;
import br.edu.ufape.catalogo.repository.ProdutoRepository;
import br.edu.ufape.catalogo.service.interfaces.IProdutoService;

@Service
public class ProdutoServiceImpl implements IProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    @Override
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }

}
