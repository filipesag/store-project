package br.com.beststore.controller;

import br.com.beststore.domain.Categoria;
import br.com.beststore.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> buscaPorId(@PathVariable Integer id) {
        Categoria cat = service.buscar(id);
        return ResponseEntity.ok().body(cat);
    }


}
