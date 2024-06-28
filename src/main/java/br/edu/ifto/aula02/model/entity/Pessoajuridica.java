package br.edu.ifto.aula02.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import org.springframework.context.annotation.Scope;

@Scope("session")
@Entity
@Table(name="pessoajuridica")
@PrimaryKeyJoinColumn(name="id_pessoa")
public class Pessoajuridica extends Pessoa{

    private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
