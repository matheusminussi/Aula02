package br.edu.ifto.aula02.model.dao;

import br.edu.ifto.aula02.model.entity.Produto;
import br.edu.ifto.aula02.model.entity.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Repository
public class VendaRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Venda venda){
        em.persist(venda);
    }

    public Venda venda(Long id){
        return em.find(Venda.class, id);
    }

    public List<Venda> Vendas(){
        Query query = em.createQuery("from Venda");
        return query.getResultList();
    }

    public void remove(Long id){
        Venda v = em.find(Venda.class, id);
        em.remove(v);
    }

    public void update(Venda venda){
        em.merge(venda);
    }

    public List<Venda> findByData(LocalDate data) {

        System.out.println("chegou repository -- " + data);



        Query query = em.createQuery("FROM Venda v WHERE v.data = :data");
        query.setParameter("data", data);
        return query.getResultList();
    }
}
