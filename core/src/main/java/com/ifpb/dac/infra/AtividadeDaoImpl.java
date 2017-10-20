package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Atividade;
import com.ifpb.dac.interfaces.AtividadeDao;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author rodrigobento
 */
@Stateless
@Remote(AtividadeDao.class)
public class AtividadeDaoImpl implements AtividadeDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void adicionar(Atividade ativ) {
        em.persist(ativ);
    }

    @Override
    public void remover(Atividade ativ) {
        em.remove(buscarPorId(ativ.getId()));
    }

    @Override
    public void atualizar(Atividade ativ) {
        em.merge(ativ);
    }

    @Override
    public List<Atividade> listarTodos() {
        return em.createQuery("SELECT a FROM Atividade a", Atividade.class).getResultList();
    }

    @Override
    public Atividade buscarPorId(String id) {
        return em.find(Atividade.class, id);
    }

    @Override
    public List<Atividade> atividadesProfessor(String professor) {
        TypedQuery<Atividade> createQuery
                = em.createQuery("SELECT a FROM Atividade a "
                        + "WHERE a.turma = ANY "
                        + "(SELECT t FROM Turma t "
                        + "JOIN t.professor p "
                        + "WHERE p.nome =:prof "
                        + "GROUP BY t.codigo_turma)", Atividade.class);
        createQuery.setParameter("prof", professor);
        List<Atividade> resultList = createQuery.getResultList();
        return resultList;
    }
    @Override
    public List<Atividade> atividadesAluno(int id) {
        Query createNativeQuery = em.createNativeQuery("SELECT * FROM atividade a "
                + "WHERE a.turma_id = ANY "
                + "(SELECT at.codigo_turma FROM aluno_turma at "
                + "INNER JOIN turma t on at.codigo_turma = t.codigo_turma "
                + "WHERE at.id = " + id + " "
                + "GROUP BY at.codigo_turma)", Atividade.class);
        List<Atividade> list = createNativeQuery.getResultList();
        return list;
    }
    
}
