
package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Pedido;
import com.ifpb.dac.enums.Tipo;
import com.ifpb.dac.interfaces.PedidoDao;
import com.ifpb.dac.rs.interfaces.PedidoDaoLocal;
import java.util.List;
import java.util.Optional;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author rodrigobento
 */
@Stateless
@Remote(PedidoDao.class)
@Local(PedidoDaoLocal.class)
public class PedidoDaoImpl implements PedidoDao,PedidoDaoLocal {

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
//        System.out.println(p.getId() + " seu id");
//        System.out.println(p.getPrioridade() + " para atualizar");
        em.merge(p);
    }

    @Override
    public List<Pedido> listarTodos() {
        return em.createQuery("SELECT p FROM Pedido p ORDER BY p.prioridade DESC", 
                Pedido.class).getResultList();
    }
    
    @Override
    public List<Pedido> listarTodosFiltro(Tipo usuario) {
        TypedQuery<Pedido> query = em.createQuery("SELECT p FROM Pedido p WHERE p.tipo=:tipo ORDER BY p.prioridade DESC", 
                Pedido.class);
        query.setParameter("tipo", usuario);
        return query.getResultList();
    }

    @Override
    public Pedido buscarPorId(int id) {
        return em.find(Pedido.class, id);
    }

    @Override
    public Pedido buscarPorCredenciais(String email, String senha) {
        TypedQuery<Pedido> createQuery = em.createQuery("SELECT p "
                + "FROM Pedido p WHERE p.email =:email AND p.senha =:senha", Pedido.class);
        createQuery.setParameter("email", email);
        createQuery.setParameter("senha", senha);
        Optional<Pedido> objeto = createQuery.getResultList().stream().findFirst();
        if(objeto.isPresent()){
            return objeto.get();
        } else {
            return null;
        }
   }

    
    
}
