package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Curso;
import com.ifpb.dac.interfaces.CursoDao;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Remote(CursoDao.class)
public class CursoDaoImpl implements CursoDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void adicionar(Curso curs) {
        em.persist(curs);
    }

    @Override
    public void remover(Curso curs) {
        Curso aux = buscarPorId(curs.getCodigo_curso());
        em.remove(aux);
    }

    @Override
    public void atualizar(Curso curs) {
        em.merge(curs);
    }

    @Override
    public List<Curso> listarTodos() {
        return em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
    }

    @Override
    public Curso buscarPorId(int id) {
        return em.find(Curso.class, id);
    }

}
