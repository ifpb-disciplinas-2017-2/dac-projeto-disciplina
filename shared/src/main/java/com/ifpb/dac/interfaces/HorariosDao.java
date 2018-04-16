package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Horario;
import com.ifpb.dac.entidades.HorariosDTO;
import com.ifpb.dac.entidades.Professor;
import java.util.Calendar;
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
    List<HorariosDTO> listarHorarioTurma(String disciplina, String professor);
    
    boolean salaDisponivel(String sala, String dia, Horario horario);
    boolean laboratorioDisponivel(String lab, String dia, Horario horario);
    boolean professorDisponivel(Professor professor, String dia, Horario horario);
    
    
}
