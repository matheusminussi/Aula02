package br.edu.ifto.aula02.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import org.springframework.context.annotation.Scope;

@Scope("session")
@Entity
@Table(name="pessoafisica")
@PrimaryKeyJoinColumn(name="id_pessoa")
public class Pessoafisica extends Pessoa{

    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
