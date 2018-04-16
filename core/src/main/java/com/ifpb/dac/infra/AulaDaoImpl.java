package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Aula;
import com.ifpb.dac.entidades.Disciplina;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.interfaces.AulaDao;
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
@Remote(AulaDao.class)
public class AulaDaoImpl implements AulaDao{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void adicionar(Aula a) {
        em.persist(a);
    }

    @Override
    public void atualizar(Aula a) {
        em.merge(a);
    }

    @Override
    public void remover(Aula a) {
        Aula auxiliar = buscarPorId(a.getCod_aula());
        em.remove(auxiliar);
    }

    @Override
    public List<Aula> listarTodos() {
        return em.createQuery("SELECT a FROM Aula a", 
                Aula.class).getResultList();
    }

    @Override
    public Aula buscarPorId(int id) {
        return em.find(Aula.class, id);
    }

    @Override
    public List<Aula> listarAulasTurma(String disciplina, Professor professor) {
        String sql = "SELECT a "
                + "FROM Aula a "
                + "JOIN a.horario h "
                + "JOIN a.laboratorio l "
                + "JOIN a.disciplina d "
                + "JOIN a.professor p "
                + "JOIN a.sala s "
                + "JOIN a.turma t "
                + "WHERE a.disciplina.descricao =:disciplina AND p.codigo =:professor "
                + "ORDER BY a.abrev_dia, h.inicio";
        TypedQuery<Aula> createQuery = em.createQuery(sql, Aula.class);
        createQuery.setParameter("disciplina", disciplina);
        createQuery.setParameter("professor", professor.getCodigo());
        return createQuery.getResultList();
    }
    
}
