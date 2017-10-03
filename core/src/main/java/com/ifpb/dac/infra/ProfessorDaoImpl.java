
package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.interfaces.ProfessorDao;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Remote(ProfessorDao.class)
public class ProfessorDaoImpl implements ProfessorDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void adicionar(Professor prof) {
        em.persist(prof);
    }

    @Override
    public void remover(Professor prof) {
        Professor auxiliar = em.find(Professor.class, prof.getCodigo());
        em.remove(auxiliar);
    }

    @Override
    public void atualizar(Professor prof) {
        em.merge(prof);
    }

    @Override
    public List<Professor> listarTodos() {
        return em.createQuery("SELECT p FROM Professor p",
                Professor.class).getResultList();
    }
    
}
