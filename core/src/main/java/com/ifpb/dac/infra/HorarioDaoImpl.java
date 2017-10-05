package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Horario;
import com.ifpb.dac.interfaces.HorarioDao;
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
@Remote(HorarioDao.class)
public class HorarioDaoImpl implements HorarioDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void adicionar(Horario h) {
        em.persist(h);
    }

    @Override
    public void atualizar(Horario h) {
        em.merge(h);
    }

    @Override
    public void remover(Horario h) {
        Horario auxiliar = buscarPorId(h.getCodigo_hora());
        em.remove(auxiliar);
    }

    @Override
    public List<Horario> listarTodos() {
        return em.createQuery("SELECT h FROM Horario h", 
                Horario.class).getResultList();
    }

    @Override
    public Horario buscarPorId(int id) {
        return em.find(Horario.class, id);
    }
    
}
