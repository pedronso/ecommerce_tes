package br.edu.ufape.catalogo.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.catalogo.model.Categoria;
import br.edu.ufape.catalogo.repository.CategoriaRepository;
import br.edu.ufape.catalogo.service.interfaces.ICategoriaService;

@Service
public class CategoriaServiceImpl implements ICategoriaService{

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }


}
