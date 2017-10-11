package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.HorariosDTO;
import com.ifpb.dac.interfaces.HorariosDao;
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
@Remote(HorariosDao.class)
public class HorariosDaoImpl implements HorariosDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public List<HorariosDTO> listarHorarioSala(String sala) {
        String sql = "SELECT new com.ifpb.dac.entidades.HorariosDTO(a.dia, "
                + "d.descricao, h.inicio, h.fim, p.nome) "
                + "FROM Aula a JOIN a.horario h "
                + "JOIN a.sala s "
                + "JOIN a.disciplina d "
                + "JOIN a.professor p "
                + "WHERE s.codigo_sala != 37 AND s.descricao =:sala "
                + "GROUP BY a.dia, a.abrev_dia, h.codigo_hora, p.codigo, d.codigo_disc "
                + "ORDER BY a.abrev_dia, h.inicio";
        TypedQuery<HorariosDTO> createQuery = em.createQuery(sql, HorariosDTO.class);
        createQuery.setParameter("sala", sala);
        List<HorariosDTO> horario = createQuery.getResultList();
        return horario;
    }
    
    @Override
    public List<HorariosDTO> listarHorarioLab(String local) {
        String sql = "SELECT new com.ifpb.dac.entidades.HorariosDTO(a.dia, "
                + "d.descricao, h.inicio, h.fim, p.nome) "
                + "FROM Aula a JOIN a.horario h "
                + "JOIN a.laboratorio l "
                + "JOIN a.disciplina d "
                + "JOIN a.professor p "
                + "WHERE l.codigo_lab != 36 AND l.descricao =:local "
                + "GROUP BY a.dia, a.abrev_dia, h.codigo_hora, p.codigo, d.codigo_disc "
                + "ORDER BY a.abrev_dia, h.inicio";
        TypedQuery<HorariosDTO> createQuery = em.createQuery(sql, HorariosDTO.class);
        createQuery.setParameter("local", local);
        List<HorariosDTO> horario = createQuery.getResultList();
        return horario;
    }
    
    @Override
    public List<HorariosDTO> listarHorarioProf(String professor) {
        String sql = "SELECT new com.ifpb.dac.entidades.HorariosDTO(a.dia, "
                + "d.descricao, h.inicio, h.fim, p.nome, l.descricao, s.descricao) "
                + "FROM Aula a "
                + "JOIN a.horario h "
                + "JOIN a.laboratorio l "
                + "JOIN a.disciplina d "
                + "JOIN a.professor p "
                + "JOIN a.sala s "
                + "WHERE p.nome =:nome "
                + "GROUP BY a.dia, a.abrev_dia, h.codigo_hora, p.codigo, d.codigo_disc, s.codigo_sala, l.codigo_lab "
                + "ORDER BY a.abrev_dia, h.inicio";
        TypedQuery<HorariosDTO> createQuery = em.createQuery(sql, HorariosDTO.class);
        createQuery.setParameter("nome", professor);
        List<HorariosDTO> horario = createQuery.getResultList();
        return horario;
    }
    
    @Override
    public List<HorariosDTO> listarHorarioCurso(String curso, String disciplina) {
        String sql = "SELECT new com.ifpb.dac.entidades.HorariosDTO(a.dia, "
                + "d.descricao, h.inicio, h.fim, p.nome, l.descricao, s.descricao) "
                + "FROM Aula a "
                + "JOIN a.horario h "
                + "JOIN a.laboratorio l "
                + "JOIN a.disciplina d "
                + "JOIN a.professor p "
                + "JOIN a.sala s "
                + "JOIN a.curso c "
                + "WHERE c.info.descricao =:curso AND d.descricao =:disciplina "
                + "GROUP BY a.dia, a.abrev_dia, h.codigo_hora, p.codigo, d.codigo_disc, s.codigo_sala, l.codigo_lab "
                + "ORDER BY a.abrev_dia, h.inicio";
        TypedQuery<HorariosDTO> createQuery = em.createQuery(sql, HorariosDTO.class);
        createQuery.setParameter("curso", curso);
        createQuery.setParameter("disciplina", disciplina);
        List<HorariosDTO> horario = createQuery.getResultList();
        return horario;
    }

}