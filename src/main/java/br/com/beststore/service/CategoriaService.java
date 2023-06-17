package br.com.beststore.service;

import br.com.beststore.domain.Categoria;
import br.com.beststore.dto.CategoriaDTO;
import br.com.beststore.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria buscar(Integer id) {
        Optional<Categoria> cat = repository.findById(id);
        return cat.get();
    }

    public List<Categoria> buscarTodasCategorias(){
        return repository.findAll();
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

    public Page<Categoria> encontrarPagina(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDTO catDto) {
        return new Categoria(catDto.getId(), catDto.getNome());
    }

}
