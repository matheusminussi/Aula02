package br.edu.ifto.aula02.model.dao;


import br.edu.ifto.aula02.model.entity.Role;
import br.edu.ifto.aula02.model.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepository {
    @PersistenceContext
    private EntityManager em;


    public void save(Role role){
        em.persist(role);
    }

    public Role role(String nome){
        TypedQuery<Role> query = em.createQuery("select r from Role r where r.nome = :nome", Role.class);
        query.setParameter("nome",nome);
        return query.getSingleResult();
    }

    public List<Role> roles(){
        Query query = em.createQuery("from Role ");
        return query.getResultList();

    }


    public void remove(String nome) {
        Role r = em.find(Role.class, nome);
        em.remove(r);
    }

    public void update(Role role) {
        em.merge(role);
    }


}
