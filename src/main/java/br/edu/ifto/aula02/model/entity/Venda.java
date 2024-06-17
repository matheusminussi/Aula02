package br.edu.ifto.aula02.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Venda implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private LocalDateTime data;

    @OneToMany(mappedBy = "venda")
    private List<ItemVenda> itens = new ArrayList<>();

    @ManyToOne
    Pessoa pessoa;

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public List<ItemVenda> getItens() {
        System.out.println(itens);
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public Double Total(){
        double valorFinal = 0.0;
        for(ItemVenda item : itens){
            valorFinal+= item.Total().doubleValue();
        }

        return valorFinal;
    }
}
