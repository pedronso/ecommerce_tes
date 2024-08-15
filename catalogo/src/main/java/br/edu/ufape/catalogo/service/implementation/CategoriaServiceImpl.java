package br.edu.ufape.catalogo.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ufape.catalogo.dto.CategoriaRequest;
import br.edu.ufape.catalogo.dto.CategoriaResponse;
import br.edu.ufape.catalogo.model.Categoria;
import br.edu.ufape.catalogo.exceptions.NotFoundException;
import br.edu.ufape.catalogo.repository.CategoriaRepository;
import br.edu.ufape.catalogo.service.interfaces.ICategoriaService;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public CategoriaResponse save(CategoriaRequest categoriaRequest) throws NotFoundException {
        Categoria categoria = categoriaRequest.toEntity();

        if (categoria.getId() != null) {
            if (!categoriaRepository.existsById(categoria.getId())) {
                throw new NotFoundException("Categoria não encontrada com o ID = " + categoria.getId());
            }
        }

        Categoria savedCategoria = categoriaRepository.save(categoria);
        return new CategoriaResponse(savedCategoria);
    }

    @Override
    public CategoriaResponse findById(Long id) throws NotFoundException {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("It doesn't exist Category with id = " + id));

        return new CategoriaResponse(categoria);
    }

    @Override
    public List<CategoriaResponse> findAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream().map(CategoriaResponse::new).toList();
    }

    @Override
    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }

}
