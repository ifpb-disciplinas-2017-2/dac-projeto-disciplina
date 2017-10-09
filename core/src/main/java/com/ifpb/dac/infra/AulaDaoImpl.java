package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Aula;
import com.ifpb.dac.interfaces.AulaDao;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rodrigobento
 */
@Stateless
@Remote(AulaDao.class)
public class AulaDaoImpl implements AulaDao{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void adicionar(Aula a) {
        em.persist(a);
    }

    @Override
    public void atualizar(Aula a) {
        em.merge(a);
    }

    @Override
    public void remover(Aula a) {
        Aula auxiliar = buscarPorId(a.getCod_aula());
        em.remove(auxiliar);
    }

    @Override
    public List<Aula> listarTodos() {
        return em.createQuery("SELECT a FROM Aula a", 
                Aula.class).getResultList();
    }

    @Override
    public Aula buscarPorId(int id) {
        return em.find(Aula.class, id);
    }
    
}
