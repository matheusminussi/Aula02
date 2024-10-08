package br.edu.ifto.aula02.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Scope("session")
@Entity
public class Venda implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private LocalDate data;

    @OneToMany(mappedBy = "venda",cascade = CascadeType.ALL)
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
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
