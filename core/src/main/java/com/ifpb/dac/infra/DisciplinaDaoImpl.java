package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Disciplina;
import com.ifpb.dac.interfaces.DisciplinaDao;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Remote(DisciplinaDao.class)
public class DisciplinaDaoImpl implements DisciplinaDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void adicionar(Disciplina disc) {
        em.persist(disc);
    }

    @Override
    public void remover(Disciplina disc) {
        Disciplina aux = buscarPorId(disc.getCodigo_disc());
        em.remove(aux);
    }

    @Override
    public void atualizar(Disciplina disc) {
        em.merge(disc);
    }

    @Override
    public List<Disciplina> listarTodos() {
        return em.createQuery("SELECT d FROM Disciplina d", Disciplina.class).
                getResultList();
    }

    @Override
    public Disciplina buscarPorId(int id) {
        return em.find(Disciplina.class, id);
    }
    
}
