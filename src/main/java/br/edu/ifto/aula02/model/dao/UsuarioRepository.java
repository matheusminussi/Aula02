package br.edu.ifto.aula02.model.dao;


import br.edu.ifto.aula02.model.entity.Pessoa;
import br.edu.ifto.aula02.model.entity.PessoaFisica;
import br.edu.ifto.aula02.model.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioRepository {
    @PersistenceContext
    private EntityManager em;


    public void saveUsuario(Usuario u){
        em.persist(u);
    }

    public Usuario usuario(String login){
        TypedQuery<Usuario> query = em.createQuery("select u from Usuario u where u.login = :login", Usuario.class);
        query.setParameter("login",login);
        return query.getSingleResult();
    }

    public List<Usuario> usuarios(){
        Query query = em.createQuery("from Usuario ");
        return query.getResultList();

    }


    public void remove(String login) {
        Usuario u = em.find(Usuario.class, login);
        em.remove(u);
    }

    public void update(Usuario usuario) {
        em.merge(usuario);
    }


}
