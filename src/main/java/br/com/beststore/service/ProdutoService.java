package br.com.beststore.service;

import br.com.beststore.domain.Categoria;
import br.com.beststore.domain.Produto;
import br.com.beststore.repository.CategoriaRepository;
import br.com.beststore.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto buscar(Integer id) {
        Optional<Produto> prod = repository.findById(id);
        return prod.get();
    }
}
