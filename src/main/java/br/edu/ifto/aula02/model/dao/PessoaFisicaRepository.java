package br.edu.ifto.aula02.model.dao;


import br.edu.ifto.aula02.model.entity.Pessoa;
import br.edu.ifto.aula02.model.entity.PessoaFisica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaFisicaRepository {
    @PersistenceContext
    private EntityManager em;

    public void saveFisica(PessoaFisica pf) {
        em.persist(pf);
    }

    public PessoaFisica pessoaFisica(Long id) {
        return em.find(PessoaFisica.class, id);
    }

    public List<PessoaFisica> pessoasFisica() {

        System.out.println("antes de fazer a busca");
        Query query = em.createQuery("from PessoaFisica ");
        System.out.println("depois da busca");
        return query.getResultList();
    }

    public void remove(Long id) {
        PessoaFisica pf = em.find(PessoaFisica.class, id);
        em.remove(pf);
    }

    public void updateFisica(PessoaFisica pessoafisica) {
        em.merge(pessoafisica);
    }


    public List<Pessoa> findByName(String nome) {

        System.out.println("chegou aqui 2 " + nome);


        Query query = em.createQuery("FROM Pessoa p WHERE p.nome = :nome");
        query.setParameter("nome", nome);
        return query.getResultList();
    }
}
