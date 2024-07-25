package br.edu.ifto.aula02.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class ItemVenda implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Double quantidade = 0.0;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Venda venda;


    public ItemVenda() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Venda getVenda() {
        return venda;
    }

    public BigDecimal Total(){

        BigDecimal valorTotal = new BigDecimal(produto.getValor()*quantidade);
        return valorTotal;//esta passando a resposta correta
    }
}
