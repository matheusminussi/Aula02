package br.edu.ifto.aula02.model.dao;

import br.edu.ifto.aula02.model.entity.Pessoa;
import br.edu.ifto.aula02.model.entity.PessoaFisica;
import br.edu.ifto.aula02.model.entity.PessoaJuridica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaRepository {

    @PersistenceContext
    private EntityManager em;

    public Pessoa pessoa(Long id) {
        return em.find(Pessoa.class, id);
    }

    public void remove(Long id) {
        Pessoa p = em.find(Pessoa.class, id);
        em.remove(p);
    }

    public void update(Pessoa pessoa) {
        em.merge(pessoa);
    }


    public List<Pessoa> findByName(String nome) {

        System.out.println("chegou aqui 2 " + nome);


        Query query = em.createQuery("FROM Pessoa p WHERE p.nome = :nome");
        query.setParameter("nome", nome);
        return query.getResultList();
    }
}