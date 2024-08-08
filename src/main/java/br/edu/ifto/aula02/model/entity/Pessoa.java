package br.edu.ifto.aula02.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotBlank
    private String nome;

    @OneToMany(mappedBy = "pessoa")
    List<Venda> vendas = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }

    public boolean tipoObjeto(String objeto){
        return this.getClass().getSimpleName().toLowerCase().equals(objeto.toLowerCase());
    }

    public String nomeClasse(){
        return this.getClass().getSimpleName().toLowerCase();
    }

}
