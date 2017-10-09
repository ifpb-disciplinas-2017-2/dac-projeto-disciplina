package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.HorarioSalaDTO;
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
    public List<HorarioSalaDTO> listarHorarioSala(String sala) {
        String sql = "SELECT new com.ifpb.dac.entidades.HorarioSalaDTO(a.dia, "
                + "d.descricao, h.inicio, h.fim, p.nome) "
                + "FROM Aula a JOIN a.horario h "
                + "JOIN a.sala s "
                + "JOIN a.disciplina d "
                + "JOIN a.professor p "
                + "WHERE s.codigo_sala != 37 AND s.descricao =:sala "
                + "GROUP BY a.dia, a.abrev_dia, h.codigo_hora, p.codigo, d.codigo_disc "
                + "ORDER BY a.abrev_dia, h.inicio";
        TypedQuery<HorarioSalaDTO> createQuery = em.createQuery(sql, HorarioSalaDTO.class);
        createQuery.setParameter("sala", sala);
        List<HorarioSalaDTO> horario = createQuery.getResultList();
        return horario;
    }

}
