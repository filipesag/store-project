package br.com.beststore.controller;

import br.com.beststore.domain.Categoria;
import br.com.beststore.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> buscaPorId(@PathVariable Integer id) {
        Categoria cat = service.buscar(id);
        return ResponseEntity.ok().body(cat);
    }

    @PostMapping
    public ResponseEntity<Categoria> inserirNovaCategoria(@RequestBody Categoria cat){
        cat = service.inserirNovaCategoria(cat);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cat.getId()).toUri();
        return ResponseEntity.created(uri).body(cat);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Integer id,@RequestBody Categoria cat) {
        cat = service.atualizarCategoria(id, cat);
        return ResponseEntity.ok().body(cat);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Categoria> deletarCategoria(@PathVariable Integer id) {
        service.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }

}
