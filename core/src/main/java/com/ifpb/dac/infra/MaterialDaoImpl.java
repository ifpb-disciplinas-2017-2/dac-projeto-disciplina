package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Material;
import com.ifpb.dac.interfaces.MaterialDao;
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
@Remote(MaterialDao.class)
public class MaterialDaoImpl implements MaterialDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void adicionar(Material m) {
        em.persist(m);
    }

    @Override
    public void remover(Material m) {
        em.remove(buscaPorId(m.getId()));
    }

    @Override
    public void atualizar(Material m) {
        em.merge(m);
    }

    @Override
    public List<Material> listarTodos() {
        return em.createQuery("SELECT m FROM Material m", Material.class).
                getResultList();
    }

    @Override
    public Material buscaPorId(String id) {
        return em.find(Material.class, id);
    }
    
    @Override
    public List<Material> materiaisProfessor(String professor){
        TypedQuery<Material> createQuery
                = em.createQuery("SELECT m FROM Material m "
                        + "WHERE m.turma = ANY "
                        + "(SELECT t FROM Turma t "
                        + "JOIN t.professor p "
                        + "WHERE p.nome =:prof "
                        + "GROUP BY t.codigo_turma)", Material.class);
        createQuery.setParameter("prof", professor);
        return createQuery.getResultList();
    }
    
//    SELECT descricao, codigo_turma FROM material m WHERE m.codigo_turma = ANY (SELECT at.codigo_turma FROM aluno_turma at INNER JOIN turma t on at.codigo_turma = t.codigo_turma WHERE at.id = 2 GROUP BY at.codigo_turma);

    @Override
    public List<Material> materiaisAluno(int id){
        Query createNativeQuery = em.createNativeQuery("SELECT * FROM material m "
                + "WHERE m.codigo_turma = ANY "
                + "(SELECT at.codigo_turma FROM aluno_turma at "
                + "INNER JOIN turma t on at.codigo_turma = t.codigo_turma "
                + "WHERE at.id = " + id + " "
                + "GROUP BY at.codigo_turma)", Material.class);
        List<Material> list = createNativeQuery.getResultList();
        return list;
    }
        
}
