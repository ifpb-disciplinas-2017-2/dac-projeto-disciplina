package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Atividade;
import com.ifpb.dac.interfaces.AtividadeDao;
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
//        if (resultList.stream().findFirst().isPresent()) {
//            return resultList;
//        } else {
//            return null;
//        }
        return resultList;
    }
    
//    SELECT * FROM atividade a WHERE a.turma_id = ANY (SELECT t.codigo_turma FROM turma t INNER JOIN professor p ON t.codigo_prof = t.codigo_prof WHERE p.nome = 'ADRIANO MARQUES DA SILVA' GROUP BY t.codigo_turma;


//    SELECT * FROM atividade a WHERE a.turma_id = 
//    ANY(SELECT t.codigo_turma FROM turma t INNER JOIN professor p ON 
//    t.codigo_prof  = p.codigo_prof WHERE p.nome = 
//    'ADRIANO MARQUES DA SILVA' GROUP BY t.codigo_turma
//    );
}
