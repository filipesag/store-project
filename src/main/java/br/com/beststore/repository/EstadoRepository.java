package br.com.beststore.repository;

import br.com.beststore.domain.Estado;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoRepository extends JpaRepository<Estado,Integer> {

    @Transactional
    public List<Estado> findAllByOrderByNome();
}
