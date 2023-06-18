package br.com.beststore.repository;

import br.com.beststore.domain.Estado;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado,Integer> {

    @Transactional
    public List<Estado> findAllByOrderByNome();
}
