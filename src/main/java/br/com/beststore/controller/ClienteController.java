package br.com.beststore.controller;

import br.com.beststore.domain.Cliente;
import br.com.beststore.dto.ClienteDTO;
import br.com.beststore.dto.NovoClienteDTO;
import br.com.beststore.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> buscaPorId(@PathVariable Integer id) {
        Cliente cliente = service.buscar(id);
        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> buscarTodosClientes(){
        List<Cliente> clienteList = service.buscarTodosClientes();
        List<ClienteDTO> list = clienteList.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClienteDTO>> buscarPagina(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                         @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                         @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                         @RequestParam(value = "direction", defaultValue = "ASC")String direction){
        Page<Cliente> clienteList = service.encontrarPagina(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> list = clienteList.map(obj -> new ClienteDTO(obj));
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Cliente> inserirNovoCliente(@Valid @RequestBody NovoClienteDTO clienteDto){
        Cliente cliente = service.fromDTO(clienteDto);
        cliente = service.inserirNovoCliente(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(cliente);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cliente> atualizarCategoria(@Valid @PathVariable Integer id, @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = service.fromDTO(clienteDTO);
        cliente = service.atualizarCliente(id, cliente);
        return ResponseEntity.ok().body(cliente);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Cliente> deletarCliente(@PathVariable Integer id) {
        service.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
