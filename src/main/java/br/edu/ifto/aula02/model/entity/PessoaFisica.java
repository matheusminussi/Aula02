package br.edu.ifto.aula02.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import org.springframework.context.annotation.Scope;

import java.io.Serializable;

@Scope("session")
@Entity
@Table(name="pessoafisica")
@PrimaryKeyJoinColumn(name="id_pessoa")
public class PessoaFisica extends Pessoa implements Serializable {

    @Min(6)
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
