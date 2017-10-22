package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Laboratorio;
import com.ifpb.dac.interfaces.LaboratorioDao;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author rodrigobento
 */
@Remote(LaboratorioDao.class)
@Stateless
public class LaboratorioDaoImpl implements LaboratorioDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void adicionar(Laboratorio lab) {
        em.persist(lab);
    }

    @Override
    public void remover(Laboratorio lab) {
        Laboratorio auxiliar = buscarPorId(lab.getCodigo_lab());
        em.remove(auxiliar);
    }

    @Override
    public void atualizar(Laboratorio lab) {
        em.merge(lab);
    }

    @Override
    public List<Laboratorio> listarTodos() {
        TypedQuery<Laboratorio> createQuery = em.createQuery("SELECT l FROM Laboratorio l WHERE l.descricao !=:desc", 
                Laboratorio.class);
        createQuery.setParameter("desc", "X");
        return createQuery.getResultList();
    }

    @Override
    public Laboratorio buscarPorId(int id) {
        return em.find(Laboratorio.class, id);
    }

    @Override
    public List<String> listarNomeLaboratorios() {
        TypedQuery<String> createQuery = em.createQuery("SELECT l.descricao FROM Laboratorio l "
                + "WHERE l.descricao !=:desc", String.class);
        createQuery.setParameter("desc", "X");
        return createQuery.getResultList();
    }
    
}
