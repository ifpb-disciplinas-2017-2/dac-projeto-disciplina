package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Turma;
import com.ifpb.dac.interfaces.TurmaDao;
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
@Stateless
@Remote(TurmaDao.class)
public class TurmaDaoImpl implements TurmaDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void adicionar(Turma t) {
        em.persist(t);
    }

    @Override
    public void atualizar(Turma t) {
        em.merge(t);
    }

    @Override
    public void remover(Turma t) {
        Turma auxiliar = buscarPorId(t.getCodigo_turma());
        em.remove(auxiliar);
    }

    @Override
    public List<Turma> listarTodos() {
        return em.createQuery("SELECT t FROM Turma t", 
                Turma.class).getResultList();
    }

    @Override
    public Turma buscarPorId(int id) {
        return em.find(Turma.class, id);
    }

    @Override
    public List<String> listarTodasDisciplinas() {
        return em.createQuery("SELECT DISTINCT(t.nome_disciplina) FROM Turma t ORDER BY t.nome_disciplina", 
                String.class).getResultList();
    }

    @Override
    public List<String> professoresDisciplina(String disciplina) {
        TypedQuery<String> createQuery = em.createQuery("SELECT p.nome FROM Turma t "
                + "JOIN t.professor p "
                + "WHERE t.nome_disciplina =:disc "
                + "GROUP BY p.codigo", String.class);
        createQuery.setParameter("disc", disciplina);
        return createQuery.getResultList();
    }
    
}
