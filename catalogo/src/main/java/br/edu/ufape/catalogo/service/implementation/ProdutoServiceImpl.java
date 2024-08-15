package br.edu.ufape.catalogo.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ufape.catalogo.dto.ProdutoRequest;
import br.edu.ufape.catalogo.dto.ProdutoResponse;
import br.edu.ufape.catalogo.exceptions.NotFoundException;
import br.edu.ufape.catalogo.model.Categoria;
import br.edu.ufape.catalogo.model.Produto;
import br.edu.ufape.catalogo.repository.CategoriaRepository;
import br.edu.ufape.catalogo.repository.ProdutoRepository;
import br.edu.ufape.catalogo.service.interfaces.IProdutoService;

@Service
public class ProdutoServiceImpl implements IProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;

    }

    @Override
    public ProdutoResponse save(ProdutoRequest produtoRequest) throws NotFoundException {
        Produto produto = produtoRequest.toEntity();

        if (produto.getId() != null) {
            if (!produtoRepository.existsById(produto.getId())) {
                throw new NotFoundException("Produto não encontrado com o ID = " + produto.getId());
            }
        }

        if (produtoRequest.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(produtoRequest.getCategoriaId())
                    .orElseThrow(() -> new NotFoundException(
                            "Categoria não encontrada com o ID = " + produtoRequest.getCategoriaId()));
            produto.setCategoria(categoria);
        }

        Produto savedProduto = produtoRepository.save(produto);
        return new ProdutoResponse(savedProduto);
    }

    @Override
    public ProdutoResponse findById(Long id) throws NotFoundException {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado com o ID = " + id));

        return new ProdutoResponse(produto);
    }

    @Override
    public List<ProdutoResponse> findAll() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(ProdutoResponse::new).toList();
    }

    @Override
    public void deleteById(Long id) {
        produtoRepository.deleteById(id);
    }

}
