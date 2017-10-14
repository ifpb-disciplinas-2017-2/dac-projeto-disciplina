
package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Atividade;
import com.ifpb.dac.interfaces.AtividadeDao;
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
@Remote(AtividadeDao.class)
public class AtividadeDaoImpl implements AtividadeDao{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void adicionar(Atividade ativ) {
        em.persist(ativ);
    }

    @Override
    public void remover(Atividade ativ) {
        em.remove(buscarPorId(ativ.getId()));
    }

    @Override
    public void atualizar(Atividade ativ) {
        em.merge(ativ);
    }

    @Override
    public List<Atividade> listarTodos() {
        return em.createQuery("SELECT a FROM Atividade a", Atividade.class).getResultList();
    }

    @Override
    public Atividade buscarPorId(String id) {
        return em.find(Atividade.class, id);
    }
    
}
