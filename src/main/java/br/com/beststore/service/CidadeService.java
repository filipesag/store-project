package br.com.beststore.service;

import br.com.beststore.domain.Cidade;
import br.com.beststore.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    public Cidade buscar(Integer id) {
        Optional<Cidade> cat = repository.findById(id);
        return cat.get();
    }

    public Cidade inserirCidade(Cidade cidade) {
        return repository.save(cidade);
    }


}
