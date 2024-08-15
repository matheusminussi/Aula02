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
public class PessoaJuridicaRepository {
    @PersistenceContext
    private EntityManager em;

    public void saveJuridica(PessoaJuridica pj) {
        em.persist(pj);
    }
    public PessoaJuridica pessoaJuridica(Long id) {
        return em.find(PessoaJuridica.class, id);
    }

    public List<PessoaJuridica> pessoasJuridica() {

        System.out.println("antes de fazer a busca");
        Query query = em.createQuery("from PessoaJuridica");
        System.out.println("depois da busca");
        return query.getResultList();
    }

    public void remove(Long id) {
        PessoaJuridica pj = em.find(PessoaJuridica.class, id);
        em.remove(pj);
    }

    public void updateJuridica(PessoaJuridica pessoaJuridica) {
        em.merge(pessoaJuridica);
    }


    public List<Pessoa> findByName(String nome) {

        System.out.println("chegou aqui 2 " + nome);


        Query query = em.createQuery("FROM Pessoa p WHERE p.nome = :nome");
        query.setParameter("nome", nome);
        return query.getResultList();
    }
}
