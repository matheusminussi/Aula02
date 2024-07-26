package br.edu.ifto.aula02.model.dao;

import br.edu.ifto.aula02.model.entity.Pessoa;
import br.edu.ifto.aula02.model.entity.Pessoafisica;
import br.edu.ifto.aula02.model.entity.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Pessoa pessoa){
        em.persist(pessoa);
    }

    public void saveFisica(Pessoafisica pf){
        em.persist(pf);
    }

    public Pessoa pessoa(Long id){
        return em.find(Pessoa.class, id);
    }

    public List<Pessoa> pessoas(){
        Query query = em.createQuery("from Pessoa");
        return query.getResultList();
    }

    public void remove(Long id){
        Pessoa p = em.find(Pessoa.class, id);
        em.remove(p);
    }

    public void update(Pessoa pessoa){
        em.merge(pessoa);
    }


}
