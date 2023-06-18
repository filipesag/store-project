package br.com.beststore.repository;

import br.com.beststore.domain.Cliente;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    Cliente findByEmail(String email);
}
