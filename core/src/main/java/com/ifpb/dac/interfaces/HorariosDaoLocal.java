/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.HorariosDTO;
import java.util.List;

/**
 *
 * @author lyndemberg
 */
public interface HorariosDaoLocal {
    List<HorariosDTO> listarHorarioSala(String sala);
    List<HorariosDTO> listarHorarioLab(String local);
    List<HorariosDTO> listarHorarioProf(String professor);
    List<HorariosDTO> listarHorarioCurso(String curso, String disciplina);
    List<HorariosDTO> listarHorarioTurma(String disciplina, String professor);
}
