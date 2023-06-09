package br.com.beststore.service;

import br.com.beststore.domain.Categoria;
import br.com.beststore.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria buscar(Integer id) {
        Optional<Categoria> cat = repository.findById(id);
        return cat.get();
    }
}
