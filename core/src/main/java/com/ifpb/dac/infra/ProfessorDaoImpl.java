
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
//        System.out.println(prof.toString());
    }

    @Override
    public void remover(Professor prof) {
        System.out.println(prof.toString());
    }

    @Override
    public void atualizar(Professor prof) {
        System.out.println(prof.toString());
    }

    @Override
    public List<Professor> listarTodos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
