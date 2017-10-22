package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Sala;
import com.ifpb.dac.interfaces.SalaDao;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
        Sala auxiliar = buscarPorId(sala.getCodigo_sala());
        em.remove(auxiliar);
    }

    @Override
    public void atualizar(Sala sala) {
        em.merge(sala);
    }

    @Override
    public List<Sala> listarTodos() {
        TypedQuery<Sala> createQuery = em.createQuery("SELECT s FROM Sala s"
                + " WHERE s.descricao !=:desc", Sala.class);
        createQuery.setParameter("desc", "X");
        return createQuery.getResultList();        
    }

    @Override
    public Sala buscarPorId(int id) {
        return em.find(Sala.class, id);
    }

    @Override
    public List<String> listarNomeSalas() {
        TypedQuery<String> createQuery = em.createQuery("SELECT s.descricao FROM Sala s "
                + "WHERE s.descricao !=:desc", String.class);
        createQuery.setParameter("desc", "X");
        return createQuery.getResultList();
    }

}
