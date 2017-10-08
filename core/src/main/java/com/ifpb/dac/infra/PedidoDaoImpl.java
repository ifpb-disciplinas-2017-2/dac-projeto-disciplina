
package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Pedido;
import com.ifpb.dac.interfaces.PedidoDao;
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
@Remote(PedidoDao.class)
public class PedidoDaoImpl implements PedidoDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void adicionar(Pedido p) {
        em.persist(p);
    }

    @Override
    public void remover(Pedido p) {
        Pedido auxiliar = buscarPorId(p.getId());
        em.remove(auxiliar);
    }

    @Override
    public void atualizar(Pedido p) {
        System.out.println("Atualizando pedido...");
        em.merge(p);
    }

    @Override
    public List<Pedido> listarTodos() {
        return em.createQuery("SELECT p FROM Pedido p ORDER BY p.prioridade DESC", 
                Pedido.class).getResultList();
    }

    @Override
    public Pedido buscarPorId(int id) {
        return em.find(Pedido.class, id);
    }
    
}
