package br.com.beststore.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

/*    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "produto_categoria",
            joinColumns = { @JoinColumn(name="categoria_id") },
            inverseJoinColumns = { @JoinColumn(name="produto_id")}
    )
    Set<Produto> produtos = new HashSet<>();*/

    public Categoria(){}

    public Categoria(String nome) {
        this.nome = nome;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria)) return false;

        Categoria categoria = (Categoria) o;

        return getId().equals(categoria.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
