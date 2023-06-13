package br.com.beststore.dto;

import br.com.beststore.domain.Categoria;

public class CategoriaDTO implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;

    public CategoriaDTO(){}

    public CategoriaDTO(Categoria cat) {
        id = cat.getId();
        nome = cat.getNome();
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
}
