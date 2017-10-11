package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.HorariosDTO;
import java.util.List;

/**
 *
 * @author rodrigobento
 */
public interface HorariosDao {
    
    List<HorariosDTO> listarHorarioSala(String sala);
    List<HorariosDTO> listarHorarioLab(String local);
    List<HorariosDTO> listarHorarioProf(String professor);
    List<HorariosDTO> listarHorarioCurso(String curso, String disciplina);
    
}
