package br.com.beststore.service;

import br.com.beststore.domain.Pedido;
import br.com.beststore.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public Pedido buscar(Integer id) {
        Optional<Pedido> prod = repository.findById(id);
        return prod.get();
    }
}
