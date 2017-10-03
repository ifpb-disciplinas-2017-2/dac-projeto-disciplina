
package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Sala;
import com.ifpb.dac.interfaces.SalaDao;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Remote(SalaDao.class)
public class SalaDaoImpl implements SalaDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void adicionar(Sala sala) {
        em.persist(sala);
    }

    @Override
    public void remover(Sala sala) {
        Sala auxiliar = em.find(Sala.class, sala.getCodigo_sala());
        em.remove(auxiliar);
    }

    @Override
    public void atualizar(Sala sala) {
        em.merge(sala);
    }

    @Override
    public List<Sala> listarTodos() {
        return em.createQuery("SELECT s FROM Sala s", Sala.class).
                getResultList();
    }

    @Override
    public Sala buscarPorId(int id) {
        return em.find(Sala.class, id);
    }
    
}
