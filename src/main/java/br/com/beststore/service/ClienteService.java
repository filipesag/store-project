package br.com.beststore.service;

import br.com.beststore.domain.Cidade;
import br.com.beststore.domain.Cliente;
import br.com.beststore.domain.Endereco;
import br.com.beststore.domain.enums.TipoCliente;
import br.com.beststore.dto.ClienteDTO;
import br.com.beststore.dto.NovoClienteDTO;
import br.com.beststore.repository.ClienteRepository;
import br.com.beststore.repository.EnderecoRepository;
import br.com.beststore.service.exceptions.DatabaseException;
import br.com.beststore.service.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente buscar(Integer id) {
        Optional<Cliente> cat = repository.findById(id);
        return cat.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Cliente> buscarTodosClientes(){
        return repository.findAll();
    }

    @Transactional
    public Cliente inserirNovoCliente(Cliente cat) {
        cat.setId(null);
        cat = repository.save(cat);
        enderecoRepository.saveAll(cat.getEnderecos());
        return cat;
    }

    public Cliente atualizarCliente(Integer id, Cliente cliente) {
        Cliente cliente2 = repository.getReferenceById(id);
        atualizarDados(cliente2, cliente);
        return repository.save(cliente);
    }

    public void deletarCliente(Integer id){
        try{
            repository.deleteById(id);
        }catch(EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    public void atualizarDados(Cliente cliente, Cliente cliente2){
        cliente.setNome(cliente2.getNome());
        cliente.setEmail(cliente2.getEmail());
    }

    public Page<Cliente> encontrarPagina(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO clienteDto) {
        return new Cliente (clienteDto.getId(), clienteDto.getNome(), clienteDto.getEmail(), null, null, null);
    }

    public Cliente fromDTO(NovoClienteDTO clienteDTO){
        Cliente cliente = new Cliente(null,clienteDTO.getNome(), clienteDTO.getEmail(), clienteDTO.getCpfOuCnpj(),null, TipoCliente.toEnum(clienteDTO.getTipo()));
        Cidade cidade = new Cidade(clienteDTO.getCidadeId(), null, null);
        Endereco end = new Endereco(null, clienteDTO.getLogradouro(), clienteDTO.getNumero(), clienteDTO.getComplemento(), clienteDTO.getBairro(), clienteDTO.getCep(), cliente, cidade);
        cliente.getEnderecos().add(end);
        cliente.getTelefones().add(clienteDTO.getTelefone1());
        if (clienteDTO.getTelefone2()!=null){
            cliente.getTelefones().add(clienteDTO.getTelefone2());
        }
        if (clienteDTO.getTelefone3()!=null){
            cliente.getTelefones().add(clienteDTO.getTelefone3());
        }
        return cliente;
    }
}
