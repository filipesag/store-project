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

    public Categoria inserirNovaCategoria(Categoria cat) {
        return repository.save(cat);
    }

    public Categoria atualizarCategoria(Integer id, Categoria cat) {
        Categoria cat2 = repository.getReferenceById(id);
        atualizarDados(cat2, cat);
        return repository.save(cat);
    }

    public void deletarCategoria(Integer id){
        repository.deleteById(id);
    }

    public void atualizarDados(Categoria cat, Categoria cat2){
        cat.setNome(cat2.getNome());
    }


}
