
package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Material;
import com.ifpb.dac.interfaces.MaterialDao;
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
@Remote(MaterialDao.class)
public class MaterialDaoImpl implements MaterialDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void adicionar(Material m) {
        em.persist(m);
    }

    @Override
    public void remover(Material m) {
        em.remove(buscaPorId(m.getId()));
    }

    @Override
    public void atualizar(Material m) {
        em.merge(m);
    }

    @Override
    public List<Material> listarTodos() {
        return em.createQuery("SELECT m FROM Material m", Material.class).
                getResultList();
    }

    @Override
    public Material buscaPorId(int id) {
        return em.find(Material.class, id);
    }
    
    
        
}
