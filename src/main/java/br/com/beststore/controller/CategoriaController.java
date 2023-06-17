package br.com.beststore.controller;

import br.com.beststore.domain.Categoria;
import br.com.beststore.dto.CategoriaDTO;
import br.com.beststore.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> buscarTodasCategorias(){
        List<Categoria> catList = service.buscarTodasCategorias();
        List<CategoriaDTO> list = catList.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<CategoriaDTO>> buscarPagina(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                           @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                           @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                           @RequestParam (value = "direction", defaultValue = "ASC")String direction){
        Page<Categoria> catList = service.encontrarPagina(page, linesPerPage, orderBy, direction);
        Page<CategoriaDTO> list = catList.map(obj -> new CategoriaDTO(obj));
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Categoria> inserirNovaCategoria(@Valid @RequestBody CategoriaDTO catDto){
        Categoria cat = service.fromDTO(catDto);
        cat = service.inserirNovaCategoria(cat);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cat.getId()).toUri();
        return ResponseEntity.created(uri).body(cat);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@Valid @PathVariable Integer id,@RequestBody CategoriaDTO catDto) {
        Categoria cat = service.fromDTO(catDto);
        cat = service.atualizarCategoria(id, cat);
        return ResponseEntity.ok().body(cat);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Categoria> deletarCategoria(@PathVariable Integer id) {
        service.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }

}
