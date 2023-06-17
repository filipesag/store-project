package br.com.beststore.controller;

import br.com.beststore.domain.Pedido;
import br.com.beststore.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> buscaPorId(@PathVariable Integer id) {
        Pedido pedido = service.buscar(id);
        return ResponseEntity.ok().body(pedido);
    }
}
