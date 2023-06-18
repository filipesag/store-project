package br.com.beststore.dto;

import br.com.beststore.domain.Cliente;
import br.com.beststore.service.validation.ClientInsert;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;


public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "O nome deve ser obrigatório.")
    @Length(min = 5, max = 120, message = "O nome deve ter entre 5 a 120 caracteres.")
    private String nome;

    @NotEmpty(message = "Email deve ser obrigatório.")
    @Email
    private String email;

    public ClienteDTO(){}

    public ClienteDTO(Cliente cliente){
        id = cliente.getId();
        nome = cliente.getNome();
        email = cliente.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
